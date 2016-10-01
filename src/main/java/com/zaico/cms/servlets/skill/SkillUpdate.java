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

    private static final Logger LOG = LogManager.getLogger(SkillServiceImpl.class);
    Skill skill = null;
    SkillService skillService = FactoryService.getSkillServiceInstance();

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
        request.setAttribute("action","/updateskill");
        request.setAttribute("button","UPDATE");
        request.getRequestDispatcher("pages/skill/skill.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String skillName = request.getParameter("skillname");
        String skillDesc = request.getParameter("skilldesc");
        try {
            skill.setName(skillName);
            skill.setDescription(skillDesc);
            skillService.updateSkill(skill);
            LOG.info("Skill "+skill.getName()+ " updated at "+new Date());
            request.setAttribute("sucMessage","Skill \""+skill.getName()+ "\" updated successfully");
        } catch (Exception e) {
            String errMess = ExceptionHandler.handleException(e);
            LOG.info(errMess);
            request.setAttribute("errMessage",errMess);
        }
        request.getRequestDispatcher("/skills").forward(request, response);

    }
}
