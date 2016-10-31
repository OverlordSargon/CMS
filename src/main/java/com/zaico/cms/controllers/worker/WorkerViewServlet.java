package com.zaico.cms.controllers.worker;

import com.zaico.cms.entities.Worker;
import com.zaico.cms.servicies.implementation.WorkerServiceImpl;
import com.zaico.cms.servicies.interfaces.WorkerService;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by ZAITNIK on 29.10.2016.
 */
@Controller
public class WorkerViewServlet {

    /**
     * Logger
     */
    private static final Logger LOG = LogManager.getLogger(WorkerServiceImpl.class);

    @Autowired
    WorkerService workerService ;

    @RequestMapping(value = "/workers", method = RequestMethod.GET)
    public ModelAndView viewAllWorkers(

    ) {
        ModelAndView mav = new ModelAndView();
        try {
            List<Worker> workers = workerService.findAllWorkers();
            mav.addObject("workers",workers);
            if ( workers.size() == 0 ) {
                mav.addObject("infoMessage","No workers yet!");
            }
        } catch (Exception e) {
            String errMes = ExceptionHandler.handleException(e);
            LOG.info(errMes);
        }
        mav.addObject("title","CMS Workers");
        mav.addObject("cmsheader","Workers");
        mav.setViewName("worker/allworkers");
        return mav;
    }

    @RequestMapping(value = "/view_worker*",method = RequestMethod.POST)
    public ModelAndView viewWorker() {
        return null;
    }


}
