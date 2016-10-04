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
@WebServlet("/updateskill")
public class SkillUpdate extends HttpServlet {

    // Logger
    private static final Logger LOG = LogManager.getLogger(SkillServiceImpl.class);
    // Skill service class instance
    SkillService skillService = FactoryService.getSkillServiceInstance();
    // Skill entity
    Skill skill = null;

    /**
     * Get method handler
     * @param request The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            skill = skillService.findSkill((long)id);
            request.setAttribute("skill",skill);
        } catch (Exception e) {
            LOG.info("Skill \""+skill.getName()+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
        }
        request.setAttribute("title","CMS Update skill");
        request.setAttribute("cmsheader","Update skill");
        request.setAttribute("action","/updateskill");
        request.setAttribute("button","UPDATE");
        request.getRequestDispatcher("pages/skill/skill.jsp").forward(request, response);

    }

    /**
     * Post method handler
     * @param request The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String skillName = request.getParameter("skillname");
        String skillDesc = request.getParameter("skilldesc");
        try {
            LOG.info("START: update skill "+skillName);
            skill.setName(skillName);
            skill.setDescription(skillDesc);
            skillService.updateSkill(skill);
            LOG.info("END: Skill "+skill.getName()+ " updated");
            request.setAttribute("sucMessage","Skill \""+skill.getName()+ "\" updated successfully");
        } catch (Exception e) {
            String errMess = ExceptionHandler.handleException(e);
            LOG.info(errMess);
            request.setAttribute("errMessage",errMess);
        }
        request.getRequestDispatcher("/skills").forward(request, response);

    }
}
