package com.zaico.cms.servlets.filters;

import com.zaico.cms.servicies.implementation.SkillServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by nzaitsev on 03.10.2016.
 */
public class MessageFilter implements Filter {
    private static final Logger LOG = LogManager.getLogger(SkillServiceImpl.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("Filter init");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.info("Filter doFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        chain.doFilter(request,response);
        if (session != null) {
            session.setAttribute("errMessage",null);
            session.setAttribute("sucMessage",null);
            session.setAttribute("infoMessage",null);
        }
    }

    public void destroy() {
        LOG.info("Filter destroy");
    }

}
