package com.zaico.cms.controllers.role;

import com.zaico.cms.entities.Role;
import com.zaico.cms.entities.Skill;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.RoleServiceImpl;
import com.zaico.cms.servicies.interfaces.RoleService;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 25.10.2016.
 */
@Controller
public class RoleViewController {

    private static final Logger LOG = LogManager.getLogger(RoleServiceImpl.class);

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/hjkb", method = RequestMethod.GET)
    protected ModelAndView allRoles() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<Role> roles = roleService.findAllRoles();
            modelAndView.addObject("roles",roles);
            LOG.info("VIEW ALL: roles");
        } catch (Exception e) {
            String errMes = ExceptionHandler.handleException(e);
            LOG.info(errMes);
        }
        modelAndView.addObject("title","CMS Roles");
        modelAndView.addObject("cmsheader","Roles");
        modelAndView.setViewName("roles/allroles");
        return  modelAndView;
    }

    @RequestMapping(value = "/view_role**/", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView skillView(@RequestParam(value = "id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Role role = null;
        try {
            role = roleService.findRole(id);
            LOG.info("VIEW: skill "+role.getRole());
            modelAndView.addObject("role",role);
            modelAndView.addObject("title","CMS role"+role.getRole());
            modelAndView.addObject("cmsheader","Role "+role.getRole());
            modelAndView.addObject("action","/role");
            modelAndView.addObject("disabled","disabled");
            modelAndView.addObject("button","BACK");
        } catch (Exception e) {
            LOG.info("Skill \""+role.getRole()+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
        }
        modelAndView.setViewName("roles/role");
        return modelAndView;
    }
}
