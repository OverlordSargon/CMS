package com.zaico.cms.controllers.user;

import com.zaico.cms.entities.User;
import com.zaico.cms.servicies.implementation.UserServiceImpl;
import com.zaico.cms.servicies.interfaces.UserService;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 26.10.2016.
 */
@Controller
public class UserViewController {

    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    UserService userService ;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView cmsSkills() {
        ModelAndView modelAndView = new ModelAndView();
        try {
        List<User> users = userService.findAllUsers();
        modelAndView.addObject("users",users);
        LOG.info("VIEW ALL: roles");
    } catch (Exception e) {
        String errMes = ExceptionHandler.handleException(e);
        LOG.info(errMes);
    }
        modelAndView.addObject("title","CMS Users");
        modelAndView.addObject("cmsheader","Users");
        modelAndView.setViewName("user/allusers");
        return  modelAndView;
    }

    @RequestMapping(value = "/view_user*", method = RequestMethod.GET)
    public ModelAndView skillView(
            @RequestParam(value = "id") int id
    ) {
        ModelAndView modelAndView = new ModelAndView();
        User user = null;
        String username = "";
        try {
            user = userService.findUser((long)id);
            username = user.getLogin();
            LOG.info("VIEW: skill "+username);
            modelAndView.addObject("user",user);
            modelAndView.addObject("title","CMS User "+username);
            modelAndView.addObject("cmsheader","User "+username);
            modelAndView.addObject("action","/user");
            modelAndView.addObject("disabled","disabled");
            modelAndView.addObject("button","BACK");
        } catch (Exception e) {
            LOG.info("Skill \""+username+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
        }
        modelAndView.setViewName("user/userview");
        return modelAndView;
    }

}

