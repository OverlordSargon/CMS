package com.zaico.cms.servlets;

import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.OrderDAO;
import com.zaico.cms.entities.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nzaitsev on 19.08.2016.
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        OrderDAO orderDAO = FactoryDAO.getOrderDAOInstance();
        boolean suc = false;
        Order work2 = null;
        String html = req.getParameter("ordernum").toString();
        try {
            Integer num = new Integer(html);
            suc = true;
            if ( suc == true) {
             work2 = orderDAO.read((long) num);
            }

        } catch (Exception e) {
            log("no paremetr");
        }
        if ( work2 == null) {
            req.setAttribute("name","No such order");
        }
        else {
            req.setAttribute("name", work2.toString());
        }

        req.setAttribute("param",html);
        req.getRequestDispatcher("pages/main.jsp").forward(req, resp);
    }
}
