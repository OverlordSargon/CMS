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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by nzaitsev on 02.09.2016.
 */
@WebServlet("/viewrole")
public class RoleViewServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(RoleServiceImpl.class);

    Role role = null;
    RoleService roleService = FactoryService.getRoleServiceInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            role = roleService.findRole(id);
            request.setAttribute("role",role);
        } catch (Exception e) {
            LOG.info("Role \""+role.getRole()+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
        }
        request.setAttribute("action","/role");
        request.setAttribute("disabled","disabled");
        request.setAttribute("button","BACK");
        request.getRequestDispatcher("pages/role/role.jsp").forward(request, response);

    }
}
