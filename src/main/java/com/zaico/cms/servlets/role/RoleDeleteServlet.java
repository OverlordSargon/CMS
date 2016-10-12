package com.zaico.cms.servlets.role;

import com.zaico.cms.entities.Role;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.RoleServiceImpl;
import com.zaico.cms.servicies.interfaces.RoleService;
import com.zaico.cms.utility.ExceptionHandler;


import org.apache.log4j.LogManager; import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by nzaitsev on 02.09.2016.
 */

public class RoleDeleteServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(RoleServiceImpl.class);
    RoleService roleService = FactoryService.getRoleServiceInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role role = null;
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            role = roleService.findRole((long)id);
            request.setAttribute("role",role);
            request.setAttribute("infoMessage","You want to delete this role. Are you sure?");
        } catch (Exception e) {
            LOG.info("Role \""+role.getRole()+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
        }
        request.setAttribute("action","/deleterole");
        request.setAttribute("button","DELETE");
        request.setAttribute("disabled","disabled");

        request.getRequestDispatcher("pages/role/role.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role role = null;
        try {
            if ( request.getParameter("id") != null) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                role = roleService.findRole((long)id);
            }
            String message = "Role \""+role.getRole()+"\" deleted successfully";
            roleService.deleteRole(role);
            LOG.info(message);
            request.setAttribute("infoMessage",message);
//            Cookie messa = new Cookie("infoMessage",message);
//            messa.setMaxAge(60);
        } catch (Exception e) {
            String message = ExceptionHandler.handleException(e);
//            request.setAttribute("errMessage",message);
            Cookie messa = new Cookie("infoMessage",message);
            messa.setMaxAge(60);

        }
        request.getRequestDispatcher("/roles").forward(request,response);
    }
}
