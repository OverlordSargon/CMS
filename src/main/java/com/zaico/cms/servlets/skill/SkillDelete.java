package com.zaico.cms.servlets.skill;

import com.zaico.cms.entities.Skill;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.SkillServiceImpl;
import com.zaico.cms.servicies.implementation.UserServiceImpl;
import com.zaico.cms.servicies.interfaces.SkillService;
import com.zaico.cms.servicies.interfaces.WorkerService;
import com.zaico.cms.utility.ErrorCode;
import com.zaico.cms.utility.ExceptionCMS;
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
 * Created by nzaitsev on 01.09.2016.
 */
@WebServlet("/deleteskill")
public class SkillDelete extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(SkillServiceImpl.class);
    SkillService skillService = FactoryService.getSkillServiceInstance();
    WorkerService workerService = FactoryService.getWorkerServiceInstance();
    Skill skill = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            skill = skillService.findSkill((long)id);

            request.setAttribute("skill",skill);
            request.setAttribute("infoMessage","You want to delete this Skill. Are you sure?");
        } catch (Exception e) {
            LOG.info("Skill \""+skill.getName()+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
        }
        request.setAttribute("title","CMS Delete skill");
        request.setAttribute("cmsheader","Delete skill");
        request.setAttribute("action","/deleteskill");
        request.setAttribute("disabled","disabled");
        request.setAttribute("button","DELETE");
        request.getRequestDispatcher("pages/skill/skill.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (workerService.findWorkersBySkill(skill.getId()).size() == 0) {
                skillService.deleteSkill(skill);
            } else {
                throw new ExceptionCMS("Can`t delete this skill", ErrorCode.SKILL_CANNOT_BE_DELETED);
            }
            String message = "Skill \""+skill.getName()+"\" deleted successfully";
            LOG.info(message);
            request.setAttribute("infoMessage",message);
        } catch (Exception e) {
            String message = ExceptionHandler.handleException(e);
            request.setAttribute("errMessage",message);
            request.setAttribute("infoMessage","Worker need this!");
        }
        request.getRequestDispatcher("/skills").forward(request,response);
    }
}
