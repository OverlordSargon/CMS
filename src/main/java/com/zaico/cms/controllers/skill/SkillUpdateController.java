package com.zaico.cms.controllers.skill;

import com.zaico.cms.entities.Skill;
import com.zaico.cms.servicies.implementation.SkillServiceImpl;
import com.zaico.cms.servicies.interfaces.SkillService;
import com.zaico.cms.utility.ErrorCode;
import com.zaico.cms.utility.ExceptionCMS;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

/**
 * Created by nzaitsev on 25.10.2016.
 */
@Controller
public class SkillUpdateController {

    /**
     * Logger
     */
    private  final Logger LOG = LogManager.getLogger(SkillServiceImpl.class);
    /**
     * Instances
     */
    /**
     * Skill service instance
     */
    @Autowired
    SkillService skillService;
    /**
     * Skill class instance
     */
    Skill skill = null;

    @RequestMapping(value = "/update_skill", method = RequestMethod.GET)
    public ModelAndView skillUpdate(
            @RequestParam(value = "id") int id,
            Model model
    ) {
        ModelAndView modelAndView = new ModelAndView();
        Skill skillUpdate = null;

        try {
            skillUpdate = skillService.findSkill((long)id);
            model.addAttribute("skill",skillUpdate);
        } catch (Exception e) {
            LOG.error("Skill \""+skillUpdate.getName()+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
        }
        modelAndView.addObject("skill",skillUpdate);
        modelAndView.addObject("title","CMS Update skill");
        modelAndView.addObject("cmsheader","Update skill "+skillUpdate.getName());
        modelAndView.addObject("action","/update_skill");
        modelAndView.addObject("button","UPDATE");
        modelAndView.setViewName("skill/skill");
        return modelAndView;
    }

    @RequestMapping(value = "/update_skill", method = RequestMethod.POST)
    public String skillUpdateExecute(
            @RequestParam(value = "skillname") String skillName,
            @RequestParam(value = "skilldesc") String skillDesc,
            @ModelAttribute("skill") Skill skill,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        Skill skillUpdate = null;
        try {
            skillUpdate = skillService.findSkill(skill.getId());
            if ( skillName.equals("") || skillDesc.equals("")) {
                throw new ExceptionCMS("Fill all fields!", ErrorCode.SKILL_CREATE_ERROR);
            }
            LOG.info("START: update skill "+skillName);
            skillUpdate.setName(skillName);
            skillUpdate.setDescription(skillDesc);
            skillService.updateSkill(skillUpdate);
            LOG.info("END: Skill "+skillUpdate.getName()+ " updated");
            redirectAttributes.addFlashAttribute("sucMessage","Skill \""+skillUpdate.getName()+ "\" updated successfully");
            return "redirect:/skills";
        } catch (Exception e) {
            ModelAndView model1 = new ModelAndView();
            String errMess = ExceptionHandler.handleException(e);
            LOG.info(errMess);
            model1.addObject("errMessage",errMess);
            model1.addObject("skill",skillUpdate);
            model1.addObject("action","/update_skill");
            model1.addObject("button","UPDATE");
            model1.setViewName("skill/skill");
            return "redirect:/skill";
        }
    }
}
