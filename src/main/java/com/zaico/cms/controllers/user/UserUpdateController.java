package com.zaico.cms.controllers.user;

import com.zaico.cms.entities.Role;
import com.zaico.cms.entities.User;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.UserServiceImpl;
import com.zaico.cms.servicies.interfaces.RoleService;
import com.zaico.cms.servicies.interfaces.UserService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 26.10.2016.
 */
@Controller
public class UserUpdateController {
    /**
     * Logger
     */
    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);
    /**
     * Service instances
     */
    @Autowired
    UserService userService ;
    @Autowired
    RoleService roleService ;


//    Костыль, замена передаче сущности в модель
    User kostilUser = null;
    @RequestMapping(value = "update_user**", method = RequestMethod.GET)
    public ModelAndView userUpdate(
            @RequestParam(value = "id") String idi,
            Model model
    ) {
        ModelAndView mav = new ModelAndView();
        User user = null;
        try {
            long id = Long.parseLong(idi);
            user = userService.findUser(id);
            mav.addObject("user",user);
            List<Role> allRoles = roleService.findAllRoles();
            mav.addObject("roles",allRoles);
            kostilUser = user;
//            model.addAttribute("user",user);
            mav.addObject("title","CMS Update skill");
            mav.addObject("cmsheader","Update skill "+user.getLogin());
            mav.addObject("action","/update_user");
            mav.addObject("button","UPDATE");
        } catch (Exception e) {
            LOG.info("User \""+user.getLogin()+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
            mav.addObject("errMessage",errMess);
        }
        mav.setViewName("user/user");
        return mav;
    }

    @RequestMapping(value = "update_user", method = RequestMethod.POST)
    public String userUpdateExecute(
            @RequestParam("username") String userName,
            @RequestParam("password") String userPass,
            @RequestParam("roles") String [] roles,
//            @ModelAttribute("user") User user,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        User user = kostilUser;
        try {
            user.setLogin(userName);
            user.setPassword(userPass);
            List<Role> userRoles = new ArrayList<Role>();
            userService.clearRoles(user);
            // if role id not null
            if (roles != null || roles.length != 0) {
                for ( String roleId: roles) {
                    // Find each role with id and add to role list
                    long rid = Long.parseLong(roleId);
                    userRoles.add(roleService.findRole(rid));
                }
            }
            // set all finded role as user role
            user.setRoles(userRoles);
            userService.updateUser(user);
            String mess = "User "+user.getLogin()+ " updated at "+new Date();
            LOG.info(mess);
            redirectAttributes.addFlashAttribute("sucMessage",mess);
        } catch (Exception e) {
            String errMess = ExceptionHandler.handleException(e);
            LOG.info(errMess);
            redirectAttributes.addFlashAttribute("errMessage",errMess);
            redirectAttributes.addFlashAttribute("user",kostilUser);
            return "redirect:/";
        }
        return "redirect:/users";
    }
}
