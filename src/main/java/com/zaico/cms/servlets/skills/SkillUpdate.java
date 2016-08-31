package com.zaico.cms.servlets.skills;

import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.SkillDAO;
import com.zaico.cms.entities.Skill;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nzaitsev on 31.08.2016.
 */
@WebServlet("/updateskill")
public class SkillUpdate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SkillDAO skillDAO = FactoryDAO.getSkillDAOInstance();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Skill skill = skillDAO.read((long)id);
            request.setAttribute("skillName",skill.getName());
            request.setAttribute("skillDesc",skill.getDescription());
        } catch (Exception e) {

        }
        request.getRequestDispatcher("pages/skill/updateskill.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String skillName = request.getParameter("skillname");
        String skillDesc = request.getParameter("skilldesc");
        SkillDAO skillDAO = FactoryDAO.getSkillDAOInstance();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Skill skill = skillDAO.read((long)id);
            skill.setName(skillName);
            skill.setDescription(skillDesc);
            skillDAO.update(skill);
        } catch (Exception e) {

        }
        request.getRequestDispatcher("pages/skill/updateskill.jsp").forward(request, response);

    }
}
