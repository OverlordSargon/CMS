package com.zaico.cms.servlets.skills;

import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.SkillDAO;
import com.zaico.cms.entities.Skill;
import com.zaico.cms.servicies.implementation.UserServiceImpl;
import com.zaico.cms.utility.ErrorCode;
import com.zaico.cms.utility.ExceptionCMS;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

    private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("pages/skill/newskill.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "";
        String skillName = request.getParameter("skillname");
        String skillDesc = request.getParameter("skilldesc");

        try {
            SkillDAO skillDAO = FactoryDAO.getSkillDAOInstance();
            Skill newSkill = new Skill(skillName,skillDesc);
            skillDAO.create(newSkill);
            message = "Skill created successfully";
            LOG.info("Skill "+skillName+" created at "+new Date());
        } catch (Exception e) {
            LOG.info("Skill "+skillName+" creation failed at "+new Date());
            ExceptionCMS exc = new ExceptionCMS("Skill not created", ErrorCode.SKILL_CREATE_ERROR);
            String errorMessage = ExceptionHandler.handleException(exc);
            request.setAttribute("message", errorMessage);
            request.getRequestDispatcher("pages/main.jsp").forward(request, response);

        }

        request.setAttribute("skillMessage",message);
        request.getRequestDispatcher("pages/skill/allskills.jsp").forward(request, response);
    }
}
