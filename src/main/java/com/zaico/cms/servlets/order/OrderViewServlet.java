package com.zaico.cms.servlets.order;

import com.zaico.cms.entities.Order;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.interfaces.OrderService;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by nzaitsev on 19.09.2016.
 */
@WebServlet("/vieworder")
public class OrderViewServlet extends HttpServlet  {

    Logger LOG = LogManager.getLogger(OrderViewServlet.class.toString());
    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = FactoryService.getOrderServiceInstance();
        Order order = null;
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            order = orderService.findOrder(id);
            request.setAttribute("order",order);
            LOG.info("Order \""+order.getOrdNumber()+ "\" founded");
            request.setAttribute("title","CMS Update Orders");
            request.setAttribute("cmsheader","Order "+order.getOrdNumber());
        } catch (Exception e) {
            LOG.info("Order \""+order.getOrdNumber()+ "\" notfounded at "+new Date());
                String errMess = ExceptionHandler.handleException(e);
            }
            request.setAttribute("action","/order");
            request.setAttribute("disabled","disabled");
            request.setAttribute("button","BACK");
            request.getRequestDispatcher("pages/order/order.jsp").forward(request, response);
    }
}
