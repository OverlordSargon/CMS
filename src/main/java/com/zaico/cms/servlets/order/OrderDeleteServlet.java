package com.zaico.cms.servlets.order;

import com.zaico.cms.entities.Order;
import com.zaico.cms.entities.Worker;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.interfaces.OrderService;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.log4j.LogManager; import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by nzaitsev on 20.09.2016.
 */
@WebServlet("/deleteorder")
public class OrderDeleteServlet extends HttpServlet {

    //logger
    Logger logger = LogManager.getLogger(OrderDeleteServlet.class);
    // Order entity
    Order order = null;
    // Services
    OrderService orderService = FactoryService.getOrderServiceInstance();

    /**
     * Get method handler
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            order = orderService.findOrder(id);
            request.setAttribute("order",order);
            request.setAttribute("infoMessage","You want to delete Order \""+order.getOrdNumber()+"\". Are you sure?");
        } catch (Exception e) {
            logger.info("Order \""+order.getOrdNumber()+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
        }
        request.setAttribute("action","/deleteorder");
        request.setAttribute("disabled","disabled");
        request.setAttribute("button","DELETE");
        request.setAttribute("title","CMS Delete Order");
        request.setAttribute("cmsheader","Delete order "+ order.getOrdNumber());
        request.getRequestDispatcher("pages/order/order.jsp").forward(request, response);
    }

    /**
     * Post method handler
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            logger.info("START: delete order "+order.getOrdNumber());
            // string dates into dates
            DateFormat timeF = new SimpleDateFormat("HH:mm");
            DateFormat dateF = new SimpleDateFormat("dd-MM-y");
            // create dates
            Date fromDate = order.getFrom();
            Date toDate = order.getTo();
            Date dateDate = order.getDate();
            // calendars for all dates
            Calendar calFrom = Calendar.getInstance();
            calFrom.setTime(fromDate);
            Calendar calTo = Calendar.getInstance();
            calTo.setTime(toDate);
            Calendar calDate = Calendar.getInstance();
            calDate.setTime(dateDate);
            orderService.findCapacity(calDate,calFrom,calTo,null,"F",order.getWorker());
            orderService.deleteOrder(order);
            String message = "Order \""+order.getOrdNumber()+"\" deleted successfully";
            request.setAttribute("infoMessage",message);
            logger.info("END: delete order "+order.getOrdNumber());
        } catch (Exception e) {
            String message = ExceptionHandler.handleException(e);
            logger.error("ERROR:order delete "+order.getOrdNumber());
            request.setAttribute("errMessage",message);
        }
        request.getRequestDispatcher("/orders").forward(request,response);
    }
}
