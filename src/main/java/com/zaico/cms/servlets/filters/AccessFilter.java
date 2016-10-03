package com.zaico.cms.servlets.filters;

import com.zaico.cms.entities.Role;
import com.zaico.cms.entities.User;
import com.zaico.cms.servicies.implementation.SkillServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nzaitsev on 03.10.2016.
 */
public class AccessFilter implements Filter {

    /**
     * Logger
     */
    private static final Logger LOG = LogManager.getLogger(SkillServiceImpl.class);

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<String>(
            Arrays.asList("", "/login", "/logout", "/register")));

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        if (session != null ) {
            User user = (User)session.getAttribute("user");
            if ( user != null) {
                boolean admin = false;
                for (Role role : user.getRoles()) {
                    if (role.getRole().equals("Administrator")) {
                        admin = true;
                        break;
                    }
                }
                if ( admin ) {
                    LOG.info("Administrator access");
                    chain.doFilter(request, response);
                } else {
                    if (req.getRequestURI().contains("order")) {
                        LOG.info("User access");
                        LOG.info(req.getRequestURI());
                        chain.doFilter(request, response);
                    } else {
                        req.setAttribute("errMessage", "You have no permission to use this.");
                        req.setAttribute("infoMessage", "Only administrator has permission.");
                        res.sendRedirect("/main");
                    }
                }
            }
        } else {
            res.sendRedirect("/login");
        }
    }

    public void destroy() {

    }

    private boolean excludeFromFilter(String path) {
        if (path.startsWith("/javax.faces.resource")) return true; // add more page to exclude here
        else return false;
    }
}
