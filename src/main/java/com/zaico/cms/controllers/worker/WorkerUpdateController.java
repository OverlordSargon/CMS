package com.zaico.cms.controllers.worker;

 import com.zaico.cms.entities.Schedule;
import com.zaico.cms.entities.Skill;
import com.zaico.cms.entities.Worker;
import com.zaico.cms.entities.Workplan;
import com.zaico.cms.servicies.implementation.WorkerServiceImpl;
import com.zaico.cms.servicies.interfaces.SkillService;
import com.zaico.cms.servicies.interfaces.WorkerService;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 02.11.2016.
 */
@Controller
public class WorkerUpdateController {
    // Logger
    private static final Logger LOG = LogManager.getLogger(WorkerServiceImpl.class);
    /**
     * Worker service class instance
     */
    @Autowired
    WorkerService workerService ;
    /**
     * SKill service class instance
     */
    @Autowired
    SkillService skillService ;

    /**
     * GET method handler
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/update_worker", method = RequestMethod.GET)
    private ModelAndView updateWorker(

            @RequestParam(value = "id") int id
    ) {
        ModelAndView mav = new ModelAndView();
        Worker worker = null;
        try {
            worker = workerService.findWorker(id);
            for ( Workplan workplan: worker.getWorkplans()) {
                for ( Schedule schedule: workplan.getSchedules() ) {
                    schedule.getId();
                }
            }
            mav.addObject("worker",worker);
            worker.getWorkplans();
            // Find worker dates and times for display
            WorkerDates workerDates = workerService.findWorkTime(worker);
            mav.addObject("dates",workerDates);
            // Find worker dates to display
            mav.addObject("workerskills",worker.getSkills());
            List<Skill> allSkills = skillService.findAllSkills();
            mav.addObject("skills",allSkills);
            mav.addObject("action","/updateworker");
            mav.addObject("button","UPDATE");
            mav.addObject("title","CMS Update worker");
            mav.addObject("cmsheader","Update worker "+worker.getName() );
            mav.setViewName("worker/worker");

        } catch (Exception e) {
            String errMess = ExceptionHandler.handleException(e);
            mav.addObject("errMessage",errMess);
            mav.addObject("worker",worker);
            mav.setViewName("worker/worker");
        } finally {
            return mav;
        }
    }
    /**
     * POST method handler
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "update_worker", method = RequestMethod.POST)
    public String updateWorkerExecute(
            @ModelAttribute("worker") Worker worker,
            @ModelAttribute("dates") WorkerDates workerDates,
            RedirectAttributes redirectAttributes
    ) {

        try {
            Worker workerUpdated = workerService.findWorker(worker.getId());
            //Get parameters
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            // Dates for schedule and workplan

            workerService.updateWorkplans(worker,workerDates);
            workerService.setSkillsFromForm(worker);

            // set all finded skill as user skill
//            worker.setName(workerName);
//            worker.setTelephone(workerNum);
//            worker.setSkills(workerSkills);
            workerService.updateWorker(worker);
            LOG.info("Worker "+worker.getName()+ " updated at "+new Date());
            redirectAttributes.addFlashAttribute("sucMessage","Worker \""+worker.getName()+ "\" updated successfully");
            return "redirect:/workers";
        } catch (Exception e) {
            String errMess = ExceptionHandler.handleException(e);
            redirectAttributes.addFlashAttribute("errMessage",errMess);
            redirectAttributes.addFlashAttribute("worker",worker);
            redirectAttributes.addAttribute("dates",workerDates);
            return "/wor";
        }
    }

}
