package com.zaico.cms.servlets.skills;

import com.zaico.cms.entities.Skill;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.UserServiceImpl;
import com.zaico.cms.servicies.interfaces.SkillService;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nzaitsev on 01.09.2016.
 */
@WebServlet("/deleteskill")
public class SkillDelete extends HttpServlet {

    private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            SkillService skillService = FactoryService.getSkillServiceInstance();
            int id = Integer.parseInt(request.getParameter("id"));
            Skill delSkill = skillService.findSkill((long)id);
            skillService.deleteSkill(delSkill);
            String message = "Skill \""+delSkill.getName()+"\" deleted successfully";
            LOG.info(message);
            request.setAttribute("sucMessage",message);
        } catch (Exception e) {
            String message = ExceptionHandler.handleException(e);
            request.setAttribute("errMessage",message);
        }
        request.getRequestDispatcher("/allskills").forward(request,response);
    }
}
