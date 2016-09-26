package com.zaico.cms.servlets.order;

import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.OrderDAO;
import com.zaico.cms.entities.Order;
import com.zaico.cms.entities.Worker;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.OrderServiceImpl;
import com.zaico.cms.servicies.implementation.WorkerServiceImpl;
import com.zaico.cms.servicies.interfaces.OrderService;
import com.zaico.cms.servicies.interfaces.WorkerService;
import com.zaico.cms.utility.ExceptionHandler;
import com.zaico.cms.utility.PrintAttributes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by nzaitsev on 24.08.2016.
 */
@WebServlet("/orders")
public class OrdersAllSerlvet extends HttpServlet{


    private static final Logger LOG = Logger.getLogger(OrderServiceImpl.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            OrderService orderService = FactoryService.getOrderServiceInstance();
            try {
                List<Order> orders = orderService.findAllOrders();
                request.setAttribute("orders", orders);
            } catch (Exception e) {
                String errMes = ExceptionHandler.handleException(e);
                LOG.info(errMes);
            }
            request.getRequestDispatcher("pages/order/allorders.jsp").forward(request, response);
        } else  {
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


}
