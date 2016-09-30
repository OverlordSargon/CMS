package com.zaico.cms.servlets.user;

import com.zaico.cms.entities.Role;
import com.zaico.cms.entities.User;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.UserServiceImpl;
import com.zaico.cms.servicies.interfaces.RoleService;
import com.zaico.cms.servicies.interfaces.UserService;
import com.zaico.cms.utility.ErrorCode;
import com.zaico.cms.utility.ExceptionCMS;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 02.09.2016.
 */
@WebServlet("/newuser")
public class UserCreateSevlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);
    RoleService roleService = FactoryService.getRoleServiceInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Role> allRoles = roleService.findAllRoles();
            request.setAttribute("roles",allRoles);
            request.setAttribute("user",new User());
            request.setAttribute("action","/newuser");
            request.setAttribute("button","CREATE");
        } catch (Exception e) {

        }
        request.getRequestDispatcher("pages/user/user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String userPass = request.getParameter("password");
//        get all role id as string massive
        String[] roles = request.getParameterValues("roles");

        try {
            UserService userService = FactoryService.getUserServiceInstance();
            User user = new User(userName,userPass);
            List<Role> userRoles = new ArrayList<Role>();
//            if role id not null
            if (roles.length != 0) {
                for ( String roleId: roles) {
//                    Find each role with id and add to role list
                    userRoles.add(roleService.findRole(Integer.parseInt(roleId)));
                }
            } else {
                throw new ExceptionCMS("You`ve choosen no roles", ErrorCode.ROLE_NOT_FOUND);
            }
//            set all founded role as user role
            user.setRoles(userRoles);
            userService.createUser(user);
            String message = "User \""+userName+"\" created ";
            LOG.info(message);
            request.setAttribute("sucMessage",message);
            request.getRequestDispatcher("/users").forward(request, response);
        } catch (Exception e) {
            String errorMessage = ExceptionHandler.handleException(e);
            request.setAttribute("errMessage"+" Try again please, check parameters", errorMessage);
        }
    }
}
