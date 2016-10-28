package com.zaico.cms.controllers.skill;

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
    public ModelAndView skillView(
            @RequestParam(value = "id") int id,
            Model model
    ) {
        ModelAndView modelAndView = new ModelAndView();
        Skill skill = null;
        try {
            skill = skillService.findSkill((long)id);
            model.addAttribute("skill",skill);
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

    Skill skillIntercator = null;
    @RequestMapping(value = "/delete_skill", method = RequestMethod.GET)
    public ModelAndView deleteSkill(
            @RequestParam(value = "id") int id,
            Model model
    ) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            skillIntercator = null;
            skillIntercator = skillService.findSkill((long)id);
            model.addAttribute("skill",skillIntercator);
            modelAndView.addObject("skill",skillIntercator);
            modelAndView.addObject("infoMessage","You want to delete this Skill. Are you sure?");
        } catch (Exception e) {
            LOG.info("Skill \""+skillIntercator.getName()+ "\" notfounded at "+new Date());
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
//            @ModelAttribute("skill") Skill skillIntercator
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

}
