package com.zaico.cms.controllers;

import com.zaico.cms.entities.Skill;
import com.zaico.cms.servicies.implementation.SkillServiceImpl;
import com.zaico.cms.servicies.interfaces.SkillService;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 20.10.2016.
 */
@Controller
public class SkillController {

    private static final Logger LOG = LogManager.getLogger(SkillServiceImpl.class);

    /**
     * Define services
     */
    @Autowired
    SkillService skillService;

    /**
     * Controller actions on URL
     */

    @RequestMapping(value = "/skills", method = RequestMethod.GET)
    @ResponseBody
    /**
     * View all skills
     */
    public ModelAndView allSkills() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<Skill> allSkills = skillService.findAllSkills();
            modelAndView.setViewName("skill/allskills");
            modelAndView.addObject("skills", allSkills);
            modelAndView.addObject("mes", "Good!");

        } catch (Exception e) {
            modelAndView.addObject("mes", "BAD!!!!");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/skill*", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView skillView(@RequestParam(value = "id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Skill skill = null;
        try {
            skill = skillService.findSkill((long)id);
            LOG.info("VIEW: skill "+skill.getName());
            modelAndView.addObject("skill",skill);
            modelAndView.addObject("title","CMS Skill "+skill.getName());
            modelAndView.addObject("cmsheader","Skill "+skill.getName());
            modelAndView.addObject("action","/skill");
            modelAndView.addObject("disabled","disabled");
            modelAndView.addObject("button","BACK");
        } catch (Exception e) {
            LOG.info("Skill \""+skill.getName()+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
        }
        modelAndView.setViewName("skill/skill");
        return modelAndView;
    }


}
