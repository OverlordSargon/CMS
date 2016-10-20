package com.zaico.cms.controllers;

import com.zaico.cms.entities.Skill;
import com.zaico.cms.servicies.interfaces.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    SkillService skillService;

    @RequestMapping(value = "/vesna", method = RequestMethod.GET)
        public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
}
