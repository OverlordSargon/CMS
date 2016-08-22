package com.zaico.cms.servlets;

import com.zaico.cms.entities.User;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.UserServiceImpl;
import com.zaico.cms.servicies.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nzaitsev on 22.08.2016.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String name = req.getAttribute("upass").toString();
//        String password = req.getAttribute("ulog").toString();
//
//        req.setAttribute("par1",name);
//        req.setAttribute("par2",password);
        req.getRequestDispatcher("pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("ulog").toString();
        String password = req.getParameter("upass").toString();
        UserService userService = FactoryService.getUserServiceInstance();
        try {
            User u = userService.login(name,password);
            if ( u != null ) {
                req.setAttribute("message","Success login "+u.getLogin());
            }
            else {
                req.setAttribute("message","failed "+u.getLogin());
            }
        }
        catch (Exception e) {
            req.setAttribute("message",e.toString());

        }

        req.setAttribute("par1",name);
        req.setAttribute("par2",password);
        req.getRequestDispatcher("pages/main.jsp").forward(req, resp);
    }
}
