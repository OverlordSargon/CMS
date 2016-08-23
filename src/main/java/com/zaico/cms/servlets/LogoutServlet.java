package com.zaico.cms.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by nzaitsev on 22.08.2016.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

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