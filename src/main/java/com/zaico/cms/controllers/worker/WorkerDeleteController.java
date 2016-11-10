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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by nzaitsev on 10.11.2016.
 */
@Controller
public class WorkerDeleteController {

    /**
     * Logger
     */
    private static final Logger LOG = LogManager.getLogger(WorkerServiceImpl.class);

    @Autowired
    WorkerService workerService;
    
    @RequestMapping(value = "delete_worker**", method = RequestMethod.GET)
    public ModelAndView deleteWorkerPrepare(
            @RequestParam("id") int id
    ) {
        ModelAndView mav = new ModelAndView();
        Worker worker = null;
        try {
            worker = workerService.findWorker((long)id);
            mav.addObject("worker",worker);
            mav.addObject("infoMessage","You want to delete this role. Are you sure?");
        } catch (Exception e) {
            String errMess = ExceptionHandler.handleException(e);
        }
        mav.addObject("action","/delete_worker  ");
        mav.addObject("disabled","disabled");
        mav.addObject("button","DELETE");
        mav.addObject("title","CMS Delete worker");
        mav.addObject("cmsheader","Delete worker");
        mav.setViewName("worker/worker");
        return mav;
    }

    @RequestMapping(value = "delete_worker*", method = RequestMethod.POST)
    public String deleteWorkerExecute(
            @RequestParam("id") int id,
            RedirectAttributes redirectAttributes
    ) {
        try {
            Worker worker = workerService.findWorker((long)id);
            workerService.deleteWorker(worker);
            String message = "Worker \""+worker.getName()+"\" deleted successfully";
            LOG.info(message);
            redirectAttributes.addFlashAttribute("infoMessage",message);
        } catch (Exception e) {
            String message = ExceptionHandler.handleException(e);
            redirectAttributes.addFlashAttribute("errMessage",message);
        }
        return "redirect:/workers";
    }
}
