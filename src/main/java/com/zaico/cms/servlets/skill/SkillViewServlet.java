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
 * Created by nzaitsev on 02.09.2016.
 */
@WebServlet("/viewskill")
public class SkillViewServlet extends HttpServlet {
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
        request.setAttribute("title","CMS Skill "+skill.getName());
        request.setAttribute("cmsheader","Skill "+skill.getName());
        request.setAttribute("action","/skill");
        request.setAttribute("disabled","disabled");
        request.setAttribute("button","BACK");
        request.getRequestDispatcher("pages/skill/skill.jsp").forward(request, response);

    }
}
