package com.zaico.cms.controllers.user;

import com.zaico.cms.entities.Role;
import com.zaico.cms.entities.User;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.UserServiceImpl;
import com.zaico.cms.servicies.interfaces.RoleService;
import com.zaico.cms.servicies.interfaces.UserService;
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nzaitsev on 26.10.2016.
 */
@Controller
public class UserCreateController {
    /**
     * Loger
     */
    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);
    /**
     * Role service instance
     */
    @Autowired
    RoleService roleService;
    /**
     * User service instance
     */
    @Autowired
    UserService userService;
    /**
     * Return new user page
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/create_user", method = RequestMethod.GET)
    protected ModelAndView createUser(Model model) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<Role> roleList = roleService.findAllRoles();
            modelAndView.addObject("roles",roleList);
            modelAndView.addObject("action","/create_user");
            modelAndView.addObject("button","CREATE");
            modelAndView.addObject("title","CMS new user");
            modelAndView.addObject("cmsheader","New user");
            modelAndView.setViewName("user/user");
            modelAndView.addObject("user",new User());
        } catch (Exception e) {

        }
        return modelAndView;
    }

    @RequestMapping(value = "/create_user", method = RequestMethod.POST)
    protected String  createUserExecute(
            @ModelAttribute("user") User user,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        try {
            List<Role> userRoles = new ArrayList<Role>();
            List<Role> rawRoles = user.getRoles();
//            // if role id not null
            if (user.getRoles().size()!= 0) {
            for ( Role roleId: user.getRoles()) {
                // Find each role with id and add to role list
                userRoles.add(roleService.findRole(Integer.parseInt(roleId.getRole())));
            }
            } else {
                throw new ExceptionCMS("You`ve choosen no roles", ErrorCode.ROLE_NOT_FOUND);
            }
            userService.clearRoles(user);
            user.setRoles(userRoles);
            userService.createUser(user);
            String message = "User \""+user.getLogin()+"\" created ";
            LOG.info(message);
            redirectAttributes.addFlashAttribute("sucMessage",message);
            return "redirect:/users";
        } catch (Exception e) {
            String errorMessage = ExceptionHandler.handleException(e);
            redirectAttributes.addFlashAttribute("errMessage",errorMessage);
            redirectAttributes.addFlashAttribute("user",user);
            return  "redirect:/create_user";
        }
    }
}
