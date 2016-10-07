package com.zaico.cms.servlets.worker;

import com.zaico.cms.entities.*;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.UserServiceImpl;
import com.zaico.cms.servicies.implementation.WorkerServiceImpl;
import com.zaico.cms.servicies.interfaces.*;
import com.zaico.cms.utility.CheckFromTo;
import com.zaico.cms.utility.DaySchedule;
import com.zaico.cms.utility.ExceptionHandler;
import com.zaico.cms.utility.WorkWeek;


import org.apache.log4j.LogManager; import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 02.09.2016.
 */
@WebServlet("/newworker")
public class WorkerCreateServlet extends HttpServlet {

    /**
     * Logger
     */
    private static final Logger LOG = LogManager.getLogger(WorkerServiceImpl.class);
    /**
     * Worker service class instance
     */
    WorkerService workerService = FactoryService.getWorkerServiceInstance();
    /**
     * Worker service class instance
     */
    SkillService skillService = FactoryService.getSkillServiceInstance();

    /**
     * GET method handler
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Skill> allSkills = skillService.findAllSkills();
            request.setAttribute("skills",allSkills);
            request.setAttribute("action","/newworker");
            request.setAttribute("button","CREATE");
            request.setAttribute("title","CMS Create worker");
            request.setAttribute("cmsheader","Create worker");
        } catch (Exception e) {

        }
        request.getRequestDispatcher("pages/worker/worker.jsp").forward(request, response);
    }

    /**
     * POST method handler
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Servicies
        WorkplanService workplanService = FactoryService.getWorkplanServiceInstance();
        ScheduleService scheduleService = FactoryService.getScheduleServiceInstance();
        try {
            // Get parameters
            String workerName = request.getParameter("workername");

            int workerNum = Integer.parseInt(request.getParameter("workertele"));
            String[] skills = request.getParameterValues("skills");
            // Sample of date
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-y");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            // Dates for schedule and workplan
            String beginDate = request.getParameter("begindate");
            String endDate = request.getParameter("enddate");
            String beginTime = request.getParameter("begintime");
            String endTime = request.getParameter("endtime");
            String breakHour = request.getParameter("breakhour");

            CheckFromTo.checkDays(beginDate,endDate);
            CheckFromTo.checkHours(beginTime,endTime);
            /*Workplans*/
            // Empty list of workplans
            List<Workplan> workplanList = new ArrayList<Workplan>();
            // Get list of dates & create workplan for these days
            List<Date> workDays = WorkWeek.getWorkDays(beginDate,endDate);
            for (Date day: workDays) {
                Workplan workplan = new Workplan(day,workerName);
                workplan.setSchedules(DaySchedule.scheduleList(beginTime,endTime,breakHour));
                workplan.setUpdatedAt(new Date());
                workplan.setCreatedAt(new Date());
                workplanService.createWorkplan(workplan);
                // Add workplan entity to workplan list
                workplanList.add(workplan);
            }
            if (workplanList.size() == 0 ) {
                String messa = "In period from "+beginDate+" to "+endDate+"only weekends";
                request.setAttribute("infoMessage",messa);
            }
            /*Skills*/
            List<Skill> workerSkills = new ArrayList<Skill>();
            // if skill id not null
            if (skills != null && skills.length != 0) {
                for ( String skillId: skills) {
                    // Find each skill with id and add to skill list
                    long id = Long.parseLong(skillId);
                    workerSkills.add(skillService.findSkill(id));
                }
            }
            // set all finded skill as user skill
            Worker worker = new Worker(workerName, workerNum);
            workerService.createWorker(worker);
            worker.setSkills(workerSkills);
            worker.setWorkplans(workplanList);
            workerService.updateWorker(worker);
            String message = "Worker \""+workerName+"\" created at "+new Date();
            LOG.info(message);
            request.setAttribute("sucMessage",message);
            request.getRequestDispatcher("/workers").forward(request, response);
        } catch (Exception e) {
            String errorMessage = ExceptionHandler.handleException(e);
            request.setAttribute("errMessage", errorMessage);
            request.getRequestDispatcher("/admin/pages/order/worker.jsp").forward(request, response);
        }
    }
}
