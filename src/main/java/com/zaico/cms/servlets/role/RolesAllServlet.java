package com.zaico.cms.servlets.role;

import com.zaico.cms.entities.Role;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.RoleServiceImpl;
import com.zaico.cms.servicies.interfaces.RoleService;
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
@WebServlet("/roles")
public class RolesAllServlet extends HttpServlet {

    // Logger
    private static final Logger LOG = LogManager.getLogger(RoleServiceImpl.class);

    /**
     * Get method handler
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            RoleService roleService = FactoryService.getRoleServiceInstance();
            try {
                List<Role> roles = roleService.findAllRoles();
                request.setAttribute("roles",roles);
                LOG.info("VIEW ALL: roles");
            } catch (Exception e) {
                String errMes = ExceptionHandler.handleException(e);
                LOG.info(errMes);
            }
            request.setAttribute("title","CMS Roles");
            request.setAttribute("cmsheader","Roles");
            request.getRequestDispatcher("pages/role/allroles.jsp").forward(request, response);
        } else  {
            response.sendRedirect("/login");
        }
    }

    /**
     * Post method handler
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
