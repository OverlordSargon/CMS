package com.zaico.cms.controllers;

import com.zaico.cms.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by nzaitsev on 12.10.2016.
 */
@Controller
public class Vesna  {
    @RequestMapping(value = "/vesna", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("mes", "HELL Spring");
        modelAndView.setViewName("vesna");
        return modelAndView;
    }
}
