package com.zaico.cms.controllers.skill;

import com.zaico.cms.entities.Skill;
import com.zaico.cms.servicies.implementation.SkillServiceImpl;
import com.zaico.cms.servicies.interfaces.SkillService;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by nzaitsev on 26.10.2016.
 */
@Controller
public class SkillCreateController {

    /**
     * Logger
     */
    private static final Logger LOG = LogManager.getLogger(SkillServiceImpl.class);

    /**
     * Define services
     */
    @Autowired
    SkillService skillService;


    @RequestMapping(value = "/create_skill", method = RequestMethod.GET)
    public ModelAndView skillNew() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("action","/create_skill");
        modelAndView.addObject("button","CREATE");
        modelAndView.addObject("title","CMS new skill");
        modelAndView.addObject("cmsheader","New skill");
        modelAndView.setViewName("skill/skill");
        return modelAndView;
    }

    @RequestMapping(value = "/create_skill", method = RequestMethod.POST)
    public  String skillNewCreate(
            @RequestParam(value = "skillname") String skillName,
            @RequestParam(value = "skilldesc") String skillDesc,
            Model model,
            RedirectAttributes redirectAttributes) {
        Skill skill = new Skill(skillName,skillDesc);
        try {
            LOG.info("START: create skill");
            skillService.createSkill(skill);
            String message = "Skill \""+skillName+"\" created!";
            LOG.info(message);
            LOG.info("END: skill "+skillName+" created");
            redirectAttributes.addFlashAttribute("sucMessage",message);
            return "redirect:/skills";
        } catch (Exception e) {
            String errorMessage = ExceptionHandler.handleException(e);
            LOG.info("!ERROR! END: skill "+skillName+"not created");
            redirectAttributes.addFlashAttribute("errMessage", errorMessage);
            redirectAttributes.addFlashAttribute("skill", skill);
            return "redirect:/create_skill";
        }
    }
}
