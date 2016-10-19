package com.zaico.cms.controllers;

import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.SkillDAO;
import com.zaico.cms.entities.Skill;
import com.zaico.cms.entities.User;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.interfaces.SkillService;
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
        SkillService skillService = FactoryService.getSkillServiceInstance();
        try {
            List<Skill> allSkills = skillService.findAllSkills();
            modelAndView.setViewName("allskills");
            modelAndView.addObject("skills", allSkills);
            modelAndView.addObject("mes", "Good!");

        } catch (Exception e) {
            modelAndView.addObject("mes", "BAD!!!!");
        }
        return modelAndView;
    }
}
