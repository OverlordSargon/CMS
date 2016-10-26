package com.zaico.cms.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by nzaitsev on 22.08.2016.
 */
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect("/login");
        } else {
//            String string ="";
//            string = req.getAttribute("errMessage").toString();
//            req.setAttribute("errMessage",string);
            req.setAttribute("title","CMS main");
            req.setAttribute("name", "EnterOrderNumber");
            req.setAttribute("date", new Date() );
            req.getRequestDispatcher("pages/main.jsp").forward(req, resp);
        }
    }


}
