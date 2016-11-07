package com.zaico.cms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ZAITNIK on 06.11.2016.
 */
@Controller
public class CmsController {

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView mainPage() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("title","CMS");
        mav.setViewName("components/cmsmn");
        return mav;
    }
}
