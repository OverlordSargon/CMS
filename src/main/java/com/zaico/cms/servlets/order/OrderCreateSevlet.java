package com.zaico.cms.servlets.order;

import com.zaico.cms.entities.*;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.WorkerServiceImpl;
import com.zaico.cms.servicies.interfaces.OrderService;
import com.zaico.cms.servicies.interfaces.ScheduleService;
import com.zaico.cms.servicies.interfaces.SkillService;
import com.zaico.cms.servicies.interfaces.WorkerService;
import com.zaico.cms.utility.ExceptionCMS;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.util.concurrent.TimeUnit.HOURS;

/**
 * Created by nzaitsev on 03.09.2016.
 */
@WebServlet("/neworder")
public class OrderCreateSevlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(WorkerServiceImpl.class);

    OrderService orderService = FactoryService.getOrderServiceInstance();
    WorkerService workerService = FactoryService.getWorkerServiceInstance();
    SkillService skillService = FactoryService.getSkillServiceInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Worker> allWorkers = workerService.findAllWorkers();
            List<Skill> allSkills = skillService.findAllSkills();
            request.setAttribute("workers",allWorkers);
            request.setAttribute("skills",allSkills);
            request.setAttribute("action","/neworder");
            request.setAttribute("button","CREATE");
        } catch (Exception e) {

        }
        request.getRequestDispatcher("pages/order/order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Get parameters

        String orderNum = request.getParameter("ordernum");
        String orderDesc = request.getParameter("orderdesc");
        Long orderSkill = Long.parseLong(request.getParameter("orderworktype"));
        String dateS = request.getParameter("orderday");
        String fromS = request.getParameter("orderfrom");
        String toS = request.getParameter("orderto");
        String orderClient = request.getParameter("ordercname");
        int orderCleintNum = Integer.parseInt(request.getParameter("ordertele"));

        ScheduleService scheduleService = FactoryService.getScheduleServiceInstance();

        try {
//         string dates into dates
            DateFormat timeF = new SimpleDateFormat("HH:mm");
            DateFormat dateF = new SimpleDateFormat("dd-MM-y");

//          create dates
            Date fromDate = timeF.parse(fromS);
            Date toDate = timeF.parse(toS);
            Date dateDate = dateF.parse(dateS);

//          calendars for all dates
            Calendar calFrom = Calendar.getInstance();
            calFrom.setTime(fromDate);

            Calendar calTo = Calendar.getInstance();
            calTo.setTime(toDate);

            Calendar calDate = Calendar.getInstance();
            calDate.setTime(dateDate);

        /*Find workers by skill*/
            Worker workerOrder = orderService.findCapacity(calDate,calFrom,calTo,orderSkill,"W",null);
            Order order = new Order(orderNum,orderDesc,dateDate,fromDate,toDate,orderCleintNum,orderClient,workerOrder);
            LOG.info(order.toString());
            orderService.createOrder(order);
            String message = "Order \""+orderNum+"\" successfuly";
            LOG.info(message);
            request.setAttribute("sucMessage",message);
            request.getRequestDispatcher("pages/order/order.jsp").forward(request, response);

        } catch (Exception e) {
            String errorMessage = ExceptionHandler.handleException(e);
            request.setAttribute("errMessage", errorMessage);
            String infoMessage = "Try again, please. Choose another time interval";
            request.setAttribute("infoMessage", infoMessage);
            doGet( request,  response);
        }
    }
}
