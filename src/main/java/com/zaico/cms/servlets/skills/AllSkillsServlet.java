package com.zaico.cms.servlets.skills;

import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.OrderDAO;
import com.zaico.cms.dao.interfaces.SkillDAO;
import com.zaico.cms.entities.Order;
import com.zaico.cms.entities.Skill;
import com.zaico.cms.utility.PrintAttributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by nzaitsev on 25.08.2016.
 */
@WebServlet("/allskills")
public class AllSkillsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SkillDAO skillDAO = FactoryDAO.getSkillDAOInstance();
        List<Skill> allSkills = skillDAO.getAll();
        String result = "";
        try {
            for (Skill skill: allSkills) {
                result = (PrintAttributes.getAttributes(skill));
            }
        } catch (Exception e) {
            result = "FUCK";
        }
        String hhhh = "<h1>{EQ{QE</h1>";
        request.setAttribute("skills",allSkills);
        request.setAttribute("html",result);
        request.getRequestDispatcher("pages/skill/allskills.jsp").forward(request, response);
    }
}
