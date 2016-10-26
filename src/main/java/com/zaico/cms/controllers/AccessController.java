package com.zaico.cms.controllers;

import com.zaico.cms.entities.Skill;
import com.zaico.cms.servicies.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by nzaitsev on 20.10.2016.
 */
public class AccessController {

    @Autowired
    UserService userService;


    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("login");
            modelAndView.addObject("infoMessage","Please login to work with CMS!");

        } catch (Exception e) {
            modelAndView.addObject("mes", "BAD!!!!");
        }
        return modelAndView;
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String loginCmsUser(HttpServletRequest request, Model model) {
        try {
            userService.findByName(request.getParameter("ulog"));
            request.setAttribute("sucMessage","U R WC3!");
        } catch (Exception e) {
            request.setAttribute("errMessage","FUCK U!");
        }
        return "/roles";
    }
}
