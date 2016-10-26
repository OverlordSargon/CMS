package com.zaico.cms.servlets.user;

import com.zaico.cms.entities.User;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.UserServiceImpl;
import com.zaico.cms.servicies.interfaces.UserService;
import com.zaico.cms.utility.ExceptionHandler;


import org.apache.log4j.LogManager; import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by nzaitsev on 01.09.2016.
 */
public class UsersAllServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            UserService userService = FactoryService.getUserServiceInstance();
            try {
                List<User> users = userService.findAllUsers();
                request.setAttribute("users", users);
            } catch (Exception e) {
                String errMes = ExceptionHandler.handleException(e);
                LOG.info(errMes);
            }
            request.setAttribute("title","CMS Users");
            request.setAttribute("cmsheader","Users");
            request.getRequestDispatcher("pages/user/allusers.jsp").forward(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
