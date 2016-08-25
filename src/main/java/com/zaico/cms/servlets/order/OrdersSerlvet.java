package com.zaico.cms.servlets.order;

import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.OrderDAO;
import com.zaico.cms.entities.Order;
import com.zaico.cms.utility.PrintAttributes;

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
public class OrdersSerlvet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDAO orderDAO = FactoryDAO.getOrderDAOInstance();
        List<Order> allOrders = orderDAO.getAll();
        String result = "";
        boolean first = true;
        try {
            for (Order order: allOrders) {
                if ( first == true ) {
                    result = PrintAttributes.getHeader(order);
                    first = false;
                }
                result += (PrintAttributes.getAttributes(order));
            }
        } catch (Exception e) {
                result = "FUCK";
        }
        String hhhh = "<h1>{EQ{QE</h1>";
        request.setAttribute("orders",allOrders);
        request.setAttribute("html",result);
        request.getRequestDispatcher("pages/order/orderall.jsp").forward(request, response);
    }


}
