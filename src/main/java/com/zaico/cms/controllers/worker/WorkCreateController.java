package com.zaico.cms.controllers.worker;

import com.zaico.cms.entities.Skill;
import com.zaico.cms.entities.Worker;
import com.zaico.cms.entities.Workplan;
import com.zaico.cms.servicies.implementation.WorkerServiceImpl;
import com.zaico.cms.servicies.interfaces.ScheduleService;
import com.zaico.cms.servicies.interfaces.SkillService;
import com.zaico.cms.servicies.interfaces.WorkerService;
import com.zaico.cms.servicies.interfaces.WorkplanService;
import com.zaico.cms.utility.CheckFromTo;
import com.zaico.cms.utility.DaySchedule;
import com.zaico.cms.utility.ExceptionHandler;
import com.zaico.cms.utility.WorkWeek;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ZAITNIK on 30.10.2016.
 */
@Controller
public class WorkCreateController {

    /**
     * Logger
     */
    private static final Logger LOG = LogManager.getLogger(WorkerServiceImpl.class);
    /**
     * Worker service class instance
     */
    @Autowired
    WorkerService workerService;
    /**
     * Worker service class instance
     */
    @Autowired
    SkillService skillService;

    @Autowired
    WorkplanService workplanService;

    @Autowired
    ScheduleService scheduleService;

    /**
     * GET method handler
     */

    @RequestMapping(value = "/create_worker", method = RequestMethod.GET)
    public ModelAndView newWorker() {
        ModelAndView mav = new ModelAndView();
        try {
            List<Skill> allSkills = skillService.findAllSkills();
            mav.addObject("skills", allSkills);
            mav.addObject("action", "/create_worker");
            mav.addObject("button", "CREATE");
            mav.addObject("title", "CMS Create worker");
            mav.addObject("cmsheader", "Create worker");
            mav.addObject("worker", new Worker());
            mav.addObject("dates", new WorkerDates());
        } catch (Exception e) {

        }
        mav.setViewName("worker/worker");
        return mav;
    }

    @RequestMapping(value = "/create_worker", method = RequestMethod.POST)
    public String newWorkerExecute(
        RedirectAttributes redirectAttributes,
        @ModelAttribute(value = "worker") Worker worker,
        @ModelAttribute(value = "dates") WorkerDates dates
    ) {
        try {

            String beginDate = dates.begindate;
            String endDate = dates.enddate;
            String beginTime = dates.beginhour;
            String endTime = dates.endhour;
            String breakHour = dates.breakstart;

            CheckFromTo.checkDays(beginDate,endDate);
            CheckFromTo.checkHours(beginTime,endTime);
            //            /*Workplans*/
            // Empty list of workplans
            List<Workplan> workplanList = new ArrayList<Workplan>();
            // Get list of dates & create workplan for these days
            List<Date> workDays = WorkWeek.getWorkDays(beginDate,endDate);
            for (Date day: workDays) {
                Workplan workplan = new Workplan(day,worker.getName());
                workplan.setSchedules(DaySchedule.scheduleList(beginTime,endTime,breakHour));
                workplan.setUpdatedAt(new Date());
                workplan.setCreatedAt(new Date());
                workplanService.createWorkplan(workplan);
                // Add workplan entity to workplan list
                workplanList.add(workplan);
            }
//
            /*Skills*/
            // if skill id not null
            List<Skill> workerSkills = new ArrayList<Skill>();
            if (worker.getSkills() != null && worker.getSkills().size() != 0) {
                for ( Skill skillId: worker.getSkills()) {
                    // Find each skill with id and add to skill list
                    if ( !skillId.getName().equals(null) ) {
                        long id = (Long.parseLong(skillId.getName()));
                        workerSkills.add(skillService.findSkill(id));
                    }
                }
            }
            List<Skill> workerNullSkills = new ArrayList<Skill>();
            worker.setSkills(workerNullSkills);
//            // set all finded skill as user skill
            workerService.createWorker(worker);
            worker.setSkills(workerSkills);
            worker.setWorkplans(workplanList);
            workerService.updateWorker(worker);
            String message = "Worker \""+worker.getName()+"\" created at "+new Date();
            LOG.info(message);
            redirectAttributes.addFlashAttribute("sucMessage",message);
        } catch (Exception e) {
            String errorMessage = ExceptionHandler.handleException(e);
        }
        return "redirect:/workers";
    }
}
