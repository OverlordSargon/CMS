package com.zaico.cms.servlets.skill;

import com.zaico.cms.entities.Skill;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.SkillServiceImpl;
import com.zaico.cms.servicies.implementation.UserServiceImpl;
import com.zaico.cms.servicies.interfaces.SkillService;
import com.zaico.cms.utility.ExceptionHandler;


import org.apache.log4j.LogManager; import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by nzaitsev on 31.08.2016.
 */
@WebServlet("/newskill")
public class SkillCreate extends HttpServlet {

    // The logger
    private static final Logger LOG = LogManager.getLogger(SkillServiceImpl.class);

    /**
     * Get method handler
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("action","/newskill");
        request.setAttribute("button","CREATE");
        request.getRequestDispatcher("pages/skill/skill.jsp").forward(request, response);
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
        String skillName = request.getParameter("skillname");
        String skillDesc = request.getParameter("skilldesc");

        try {
            LOG.info("START: create skill");
            SkillService skillService = FactoryService.getSkillServiceInstance();
            Skill skill = new Skill(skillName,skillDesc);
            skillService.createSkill(skill);
            String message = "Skill \""+skillName+"\" created!";
            LOG.info(message);
            request.setAttribute("title","CMS Create skill");
            request.setAttribute("cmsheader","Create skill");
            request.setAttribute("sucMessage",message);
            //request.getSession().setAttribute("sucMessage",message);
            LOG.info("END: skill "+skillName+" created");
        } catch (Exception e) {
            String errorMessage = ExceptionHandler.handleException(e);
            LOG.info("!ERROR! END: skill "+skillName+"not created");
            request.setAttribute("errMessage", errorMessage);
        }
        request.getRequestDispatcher("/skills").forward(request, response);
        //response.sendRedirect("/skills");
    }
}
