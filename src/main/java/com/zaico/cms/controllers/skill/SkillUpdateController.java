package com.zaico.cms.controllers.skill;

import com.zaico.cms.controllers.SkillController;
import com.zaico.cms.entities.Skill;
import com.zaico.cms.servicies.implementation.SkillServiceImpl;
import com.zaico.cms.servicies.interfaces.SkillService;
import com.zaico.cms.utility.ErrorCode;
import com.zaico.cms.utility.ExceptionCMS;
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

/**
 * Created by nzaitsev on 25.10.2016.
 */
@Controller
public class SkillUpdateController {

    /**
     * Logger
     */
    private  final Logger LOG = LogManager.getLogger(SkillServiceImpl.class);
    SkillController skillController = new SkillController();
    /**
     * Instances
     */
    /**
     * Skill service instance
     */
    @Autowired
     SkillService skillService;
    /**
     * Skill class instance
     */
    Skill skill = null;




}
