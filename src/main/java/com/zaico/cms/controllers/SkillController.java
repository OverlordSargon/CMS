package com.zaico.cms.controllers;

import com.zaico.cms.entities.Skill;
import com.zaico.cms.servicies.implementation.SkillServiceImpl;
import com.zaico.cms.servicies.interfaces.SkillService;
import com.zaico.cms.servicies.interfaces.WorkerService;
import com.zaico.cms.utility.ErrorCode;
import com.zaico.cms.utility.ExceptionCMS;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 20.10.2016.
 */
@Controller
public class SkillController {

    /**
     * Skill class instance for methods interaction
     */
    Skill skillIntercator = null;

    /**
     * Logger
     */
    private static final Logger LOG = LogManager.getLogger(SkillServiceImpl.class);

    /**
     * Define services
     */
    @Autowired
    SkillService skillService;

    @Autowired
    WorkerService workerService;

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
            modelAndView.addObject("title","CMS Skills");
            modelAndView.addObject("cmsheader","Skills");
        } catch (Exception e) {

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


    @RequestMapping(value = "/delete_skill", method = RequestMethod.GET)
    public ModelAndView deleteSkill(@RequestParam(value = "id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Skill skill = null;
        try {
            skill = skillService.findSkill((long)id);
            modelAndView.addObject("skill",skill);
            modelAndView.addObject("infoMessage","You want to delete this Skill. Are you sure?");
            skillIntercator = skill;
        } catch (Exception e) {
            LOG.info("Skill \""+skill.getName()+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
        }
        modelAndView.addObject("title","CMS Delete skill");
        modelAndView.addObject("cmsheader","Delete skill");
        modelAndView.addObject("action","/delete_skill");
        modelAndView.addObject("disabled","disabled");
        modelAndView.addObject("button","DELETE");
        modelAndView.setViewName("skill/skill");
        return modelAndView;
    }

    @RequestMapping(value = "/delete_skill", method = RequestMethod.POST)
    public String deleteSkill(
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        Skill skill = skillIntercator;
        try {
            LOG.info("START: delete skill"+skill.getName());
            if (workerService.findWorkersBySkill(skill.getId()).size() == 0) {
                skillService.deleteSkill(skill);
            } else {
                throw new ExceptionCMS("Can`t delete this skill", ErrorCode.SKILL_CANNOT_BE_DELETED);
            }
            String message = "Skill \""+skill.getName()+"\" deleted successfully";
            LOG.info(message);
            skillIntercator = null;
            redirectAttributes.addFlashAttribute("sucMessage",message);
            LOG.info("END: deleted "+skill.getName());
            return "redirect:/skills";
        } catch (Exception e) {
            String message = ExceptionHandler.handleException(e);
            redirectAttributes.addFlashAttribute("errMessage",message);
            return "redirect:/skill?id="+skillIntercator.getId();

        }
    }

    Skill skillUpdate = null;
    @RequestMapping(value = "/update_skill", method = RequestMethod.GET)
    public  ModelAndView skillUpdate(@RequestParam(value = "id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            skillUpdate = skillService.findSkill((long)id);
        } catch (Exception e) {
            LOG.error("Skill \""+skillUpdate.getName()+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
            ModelAndView tmp = allSkills();
            tmp.addObject("errMessage",errMess);
            return tmp;
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
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        try {
            if ( skillName.equals("") || skillDesc.equals("")) {
                throw new ExceptionCMS("Fill all fields!", ErrorCode.SKILL_CREATE_ERROR);
            }
            LOG.info("START: update skill "+skillName);
            skillUpdate.setName(skillName);
            skillUpdate.setDescription(skillDesc);
            skillService.updateSkill(skillUpdate);
            LOG.info("END: Skill "+skillUpdate.getName()+ " updated");
            redirectAttributes.addFlashAttribute("sucMessage","Skill \""+skillUpdate.getName()+ "\" updated successfully");
            skillUpdate = null;
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
