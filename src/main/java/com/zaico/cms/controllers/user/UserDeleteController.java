package com.zaico.cms.controllers.user;

import com.zaico.cms.entities.User;
import com.zaico.cms.servicies.implementation.UserServiceImpl;
import com.zaico.cms.servicies.interfaces.UserService;
import com.zaico.cms.utility.ErrorCode;
import com.zaico.cms.utility.ExceptionCMS;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by nzaitsev on 26.10.2016.
 */
@Controller
public class UserDeleteController {

    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    UserService userService ;

    User userExecutor = null;

    @RequestMapping(value = "delete_user*", method = RequestMethod.GET)
    protected ModelAndView deleteUserView(
            @RequestParam(value = "id") String id
    ) {
        ModelAndView mav = new ModelAndView();
        try {
            userExecutor = userService.findUser(Integer.parseInt(id));
            mav.addObject("user",userExecutor);
            mav.addObject("infoMessage","You want to delete this user. Are you sure?");
            mav.addObject("title","CMS: Delete user");
            mav.addObject("cmsheader","Delete user "+userExecutor.getLogin());
            mav.addObject("action","/delete_user");
            mav.addObject("button","DELETE");
            mav.addObject("disabled","disabled");
            mav.setViewName("user/userview");
        } catch (Exception e) {
            LOG.info("User \""+userExecutor.getLogin()+ "\" not founded at ");
            String errMess = ExceptionHandler.handleException(e);
        }
        return mav;
    }
//

    @RequestMapping(value = "delete_user*", method = RequestMethod.POST)
    protected String deleteUserExecute(
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        User user = null;
        try {
            user = userExecutor;

            if ( user.equals(userExecutor.getLogin())) {
                throw new ExceptionCMS("You can`t delete youself!", ErrorCode.USER_CANNOT_BE_DELETED);
            }
            String message = "User \""+user.getLogin()+"\" deleted successfully";
            userService.deleteUser(userExecutor);
            LOG.info(message);
            userExecutor = null;
            redirectAttributes.addFlashAttribute("infoMessage",message);
        } catch (Exception e) {
            String message = ExceptionHandler.handleException(e);
            redirectAttributes.addFlashAttribute("errMessage",message);
            return "/skills";
        }
        return "redirect:/skills";
    }

}
