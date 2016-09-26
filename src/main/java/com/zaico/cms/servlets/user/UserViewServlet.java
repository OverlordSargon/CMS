package com.zaico.cms.servlets.user;

import com.zaico.cms.entities.User;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.UserServiceImpl;
import com.zaico.cms.servicies.interfaces.UserService;
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
import java.util.Date;

/**
 * Created by nzaitsev on 02.09.2016.
 */
@WebServlet("/viewuser")
public class UserViewServlet extends HttpServlet {



    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);
    UserService userService = FactoryService.getUserServiceInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            user = userService.findUser((long)id);
            request.setAttribute("user",user);
        } catch (Exception e) {
            LOG.info("User \""+user.getLogin()+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
        }
        request.setAttribute("action","/user");
        request.setAttribute("disabled","disabled");
        request.setAttribute("button","BACK");
        request.getRequestDispatcher("pages/user/user.jsp").forward(request, response);

    }
}
