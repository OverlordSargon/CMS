package com.zaico.cms.servlets;

import com.zaico.cms.entities.User;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.UserServiceImpl;
import com.zaico.cms.servicies.interfaces.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by nzaitsev on 22.08.2016.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("ulog").toString();
        String password = request.getParameter("upass").toString();
        UserService userService = FactoryService.getUserServiceInstance();
        try {
            User user = userService.login(name, password);
            String username = user.getLogin();
            if (user != null) {
//                Success message
                request.setAttribute("message", "Success login " + username);
//                Star session
                HttpSession session = request.getSession();
                session.setAttribute("user", username);
                //setting session to expiry in 30 mins
                session.setMaxInactiveInterval(30 * 60);
                Cookie userName = new Cookie("user", username);
                userName.setMaxAge(30 * 60);
                response.addCookie(userName);
                response.sendRedirect("LoginSuccess.jsp");

            } else {
                request.setAttribute("message", "failed " + username);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
                PrintWriter out = response.getWriter();
                out.println("<font color=red>Either user name or password is wrong.</font>");
            }
        }
        catch (Exception e) {
            request.setAttribute("message","Login error. Wrong credentials or no user in base.");
        }

        request.setAttribute("par1",name);
        request.setAttribute("par2",password);
        request.getRequestDispatcher("pages/main.jsp").forward(request, response);
    }
}
