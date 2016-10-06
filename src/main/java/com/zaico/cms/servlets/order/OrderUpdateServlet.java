package com.zaico.cms.servlets.order;

import com.zaico.cms.entities.Order;
import com.zaico.cms.entities.Skill;
import com.zaico.cms.entities.Worker;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.interfaces.OrderService;
import com.zaico.cms.servicies.interfaces.ScheduleService;
import com.zaico.cms.servicies.interfaces.SkillService;
import com.zaico.cms.utility.CheckFromTo;
import com.zaico.cms.utility.ErrorCode;
import com.zaico.cms.utility.ExceptionCMS;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 01.10.2016.
 */
@WebServlet("/updateorder")
public class OrderUpdateServlet extends HttpServlet {
    //logger
    Logger logger = LogManager.getLogger(OrderUpdateServlet.class);
    // servecies
    OrderService orderService = FactoryService.getOrderServiceInstance();
    SkillService skillService = FactoryService.getSkillServiceInstance();
    Order order = null;
    /**
     * Get method handler
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if ( order == null ) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                order = orderService.findOrder(id);
            }
            request.setAttribute("order",order);
            List<Skill> allSkills = skillService.findAllSkills();
            request.setAttribute("skills",allSkills);

        } catch (Exception e) {
            logger.info("Order \""+order.getOrdNumber()+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
            request.setAttribute("errMessage",errMess);
            request.getRequestDispatcher("pages/order/order.jsp").forward(request, response);
        }
        request.setAttribute("button","UPDATE");
        request.setAttribute("action","/updateorder");
        request.setAttribute("button","UPDATE");
        request.setAttribute("title","CMS Update Orders");
        request.setAttribute("cmsheader","Update order "+order.getOrdNumber());
        request.getRequestDispatcher("pages/order/order.jsp").forward(request, response);
    }

    /**
     * Post method handler
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get parameters
        try {
            logger.info("START: update order "+order.getOrdNumber());
            String orderNum = request.getParameter("ordernum");
            String orderDesc = request.getParameter("orderdesc");
            Long orderSkill = Long.parseLong(request.getParameter("orderworktype"));
            String dateS = request.getParameter("orderday");
            String fromS = request.getParameter("orderfrom");
            String toS = request.getParameter("orderto");
            String orderClient = request.getParameter("ordercname");
            int orderCleintNum = Integer.parseInt(request.getParameter("ordertele"));
            Worker workerOrder = null;
            CheckFromTo.checkHours(fromS,toS);
            ScheduleService scheduleService = FactoryService.getScheduleServiceInstance();
            /*Set old flags as F*/
            Calendar calendarD = Calendar.getInstance();
            Calendar calendarF = Calendar.getInstance();
            Calendar calendarT = Calendar.getInstance();
            calendarD.setTime(order.getDate());
            calendarF.setTime(order.getFrom());
            calendarT.setTime(order.getTo());

            //string dates into dates
            DateFormat timeF = new SimpleDateFormat("HH:mm");
            DateFormat dateF = new SimpleDateFormat("dd-MM-y");

            //create dates
            Date fromDate = timeF.parse(fromS);
            Date toDate = timeF.parse(toS);
            Date dateDate = dateF.parse(dateS);

            //calendars for all dates
            Calendar calFrom = Calendar.getInstance();
            calFrom.setTime(fromDate);

            Calendar calTo = Calendar.getInstance();
            calTo.setTime(toDate);

            Calendar calDate = Calendar.getInstance();
            calDate.setTime(dateDate);

            /*Find workers by skill*/
            orderService.findCapacity(calDate,calFrom,calTo,orderSkill,"W",null);
            workerOrder = orderService.findCapacity(calendarD,calendarF,calendarT,null,"F",order.getWorker());

            order.setOrdNumber(orderNum);
            order.setDescription(orderDesc);
            order.setDate(dateDate);
            order.setFrom(fromDate);
            order.setTo(toDate);
            order.setTelNumber(orderCleintNum);
            order.setClientName(orderClient);
            order.setWorker(workerOrder);

            logger.info("New "+order.toString());

            orderService.updateOrder(order);
            String message = "Order \""+orderNum+"\"updated successfully";
            request.setAttribute("sucMessage",message);
            request.getRequestDispatcher("orders").forward(request, response);

            logger.info("END: successful update order "+order.getOrdNumber());
        } catch (Exception e) {
            String errorMessage = ExceptionHandler.handleException(e);
            request.setAttribute("errMessage", errorMessage);
            String infoMessage = "Try again, please.";
            request.setAttribute("infoMessage", infoMessage);
            logger.error("END:update order error "+order.getOrdNumber());
//            request.getRequestDispatcher("pages/order/order.jsp").forward(request, response);
            doGet(request,response);
        }
    }
}
