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

/**
 * Created by nzaitsev on 26.10.2016.
 */
@Controller
public class UserDeleteController {

    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    UserService userService ;
    User user = null;

    @RequestMapping(value = "delete_user*", method = RequestMethod.GET)
    protected ModelAndView deleteUserView(
            @RequestParam(value = "id") String id
    ) {
        ModelAndView mav = new ModelAndView();
        try {
            user = userService.findUser(Integer.parseInt(id));
            mav.addObject("user",user);
            mav.addObject("infoMessage","You want to delete this user. Are you sure?");
            mav.addObject("title","CMS: Delete user");
            mav.addObject("cmsheader","Delete user "+user.getLogin());
            mav.addObject("action","/delete_user");
            mav.addObject("button","DELETE");
            mav.setViewName("user/user");
        } catch (Exception e) {
            LOG.info("User \""+user.getLogin()+ "\" not founded at ");
            String errMess = ExceptionHandler.handleException(e);
        }
        return mav;
    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//
//            String sessionUser = "";
//            HttpSession session = request.getSession();
//            sessionUser = session.getAttribute("user").toString();
//            Integer id = Integer.parseInt(request.getParameter("id"));
//            user = userService.findUser(id);
//
//            if ( sessionUser.equals(user.getLogin())) {
//                throw new ExceptionCMS("You can`t delete youself!", ErrorCode.USER_CANNOT_BE_DELETED);
//            }
//            userService.deleteUser(user);
//            String message = "User \""+user.getLogin()+"\" deleted successfully";
//            LOG.info(message);
//            request.setAttribute("infoMessage",message);
//        } catch (Exception e) {
//            String message = ExceptionHandler.handleException(e);
//            request.setAttribute("errMessage",message);
//        }
//        request.getRequestDispatcher("/users").forward(request,response);
//    }

}
