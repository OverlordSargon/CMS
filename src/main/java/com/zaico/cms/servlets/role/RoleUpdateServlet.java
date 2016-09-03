package com.zaico.cms.servlets.role;

import com.zaico.cms.entities.Role;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.RoleServiceImpl;
import com.zaico.cms.servicies.interfaces.RoleService;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/updaterole")
/**
 * Created by nzaitsev on 01.09.2016.
 */
public class RoleUpdateServlet extends HttpServlet {

    private static final Log LOG = LogFactory.getLog(RoleServiceImpl.class);

    Role role = null;
    RoleService roleService = FactoryService.getRoleServiceInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            role = roleService.findRole((long)id);
            request.setAttribute("role",role);
        } catch (Exception e) {
            LOG.info("Role \""+role.getRole()+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
        }
        request.setAttribute("action","/updaterole");
        request.setAttribute("button","UPDATE");
        request.getRequestDispatcher("pages/role/role.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roleName = request.getParameter("rolename");
        String roleDesc = request.getParameter("roledesc");
        try {
            role.setRole(roleName);
            role.setDescription(roleDesc);
            roleService.updateRole(role);
            LOG.info("Role "+role.getRole()+ " updated at "+new Date());
            request.setAttribute("sucMessage","Role \""+role.getRole()+ "\" updated successfully");
        } catch (Exception e) {
            String errMess = ExceptionHandler.handleException(e);
            LOG.info(errMess);
            request.setAttribute("errMessage",errMess);
        }
        request.getRequestDispatcher("/roles").forward(request, response);
    }
}
