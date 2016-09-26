package com.zaico.cms.servlets.role;

import com.zaico.cms.entities.Role;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.RoleServiceImpl;
import com.zaico.cms.servicies.interfaces.RoleService;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by nzaitsev on 01.09.2016.
 */
@WebServlet("/newrole")
public class RoleCreateServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(RoleServiceImpl.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("action","/newrole");
        request.setAttribute("button","CREATE");
        request.setAttribute("role",new Role());
        request.getRequestDispatcher("pages/role/role.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roleName = request.getParameter("rolename");
        String roleDesc = request.getParameter("roledesc");

        try {
            RoleService roleService = FactoryService.getRoleServiceInstance();
            Role role = new Role(roleName,roleDesc);
            roleService.createRole(role);
            String message = "Role \""+roleName+"\" created at "+new Date();
            LOG.info(message);
            request.setAttribute("sucMessage",message);
        } catch (Exception e) {
            String errorMessage = ExceptionHandler.handleException(e);
            request.setAttribute("errMessage"+" Try again please, check parameters", errorMessage);
        }
        request.getRequestDispatcher("/roles").forward(request, response);
    }
}
