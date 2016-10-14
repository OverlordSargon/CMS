package com.zaico.cms.controllers;

import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.SkillDAO;
import com.zaico.cms.entities.Skill;
import com.zaico.cms.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by nzaitsev on 12.10.2016.
 */
@Controller
public class Vesna  {
    @RequestMapping(value = "/vesna", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        SkillDAO skillDAO = FactoryDAO.getSkillDAOInstance();
        List<Skill> allSkills = skillDAO.getAll();
        modelAndView.addObject("skills", allSkills);
        modelAndView.setViewName("allskills");
        return modelAndView;
    }
}
