package com.zaico.cms.servlets;

import com.zaico.cms.servicies.implementation.UserServiceImpl;
import com.zaico.cms.utility.ExceptionCMS;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;

/**
 * Created by nzaitsev on 22.08.2016.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //invalidate the session if exists
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }

//        Get all cookies
        Cookie[] cookies = request.getCookies();
//        Logging user logout
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    LOG.info("Logout " + cookie.getValue() + new Date() );                }
            }
        }

        if(cookies != null){
//            Clear all cookies
            for (Cookie c : cookies) {
                Cookie cookie = c;
                c.setValue("");
                c.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        response.sendRedirect("/main");
    }

}