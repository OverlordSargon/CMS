package com.zaico.cms.servlets.worker;

import com.zaico.cms.entities.*;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.UserServiceImpl;
import com.zaico.cms.servicies.implementation.WorkerServiceImpl;
import com.zaico.cms.servicies.interfaces.*;
import com.zaico.cms.utility.*;


import org.apache.log4j.LogManager;
import org.apache.log4j.LogManager; import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 02.09.2016.
 */
@WebServlet("/updateworker")
public class WorkerUpdateServlet extends HttpServlet {

    // Logger
    private static final Logger LOG = LogManager.getLogger(WorkerServiceImpl.class);
    /**
     * Worker service class instance
     */
    WorkerService workerService = FactoryService.getWorkerServiceInstance();
    /**
     * SKill service class instance
     */
    SkillService skillService = FactoryService.getSkillServiceInstance();
    /**
     * Worker entity
     */
    Worker worker = null;

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
            long id = Long.parseLong(request.getParameter("id"));
            worker = workerService.findWorker(id);
            request.setAttribute("worker",worker);
            workerService.findWorkTime(worker,request);
            List<Skill> allSkills = skillService.findAllSkills();
            request.setAttribute("skills",allSkills);

        } catch (Exception e) {
            LOG.info("Worker \""+worker.getName()+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
        }
        request.setAttribute("action","/updateworker");
        request.setAttribute("button","UPDATE");
        request.setAttribute("title","CMS Update worker");
        request.setAttribute("cmsheader","Update worker "+worker.getName() );
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

        WorkplanService workplanService = FactoryService.getWorkplanServiceInstance();
        ScheduleService scheduleService = FactoryService.getScheduleServiceInstance();

        try {
            //Get parameters
            String workerName = request.getParameter("workername");
            int workerNum = Integer.parseInt(request.getParameter("workertele"));
            String[] skills = request.getParameterValues("skills");
            // Sample of date
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-y HH:mm");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            // Dates for schedule and workplan
            String beginDate = request.getParameter("begindate");
            String endDate = request.getParameter("enddate");
            String beginTime = request.getParameter("begintime");
            String endTime = request.getParameter("endtime");
            String breakHour = request.getParameter("breakhour");

            /*Workplans*/
            // Empty list of workplans
            List<Workplan> workplanList = new ArrayList<Workplan>();
            // Get list of dates & create workplan for these days
            List<Date> workDays = WorkWeek.getWorkDays(beginDate,endDate);
            for (Date day: workDays) {
                List<Schedule> scheduleList = DaySchedule.scheduleList(beginTime,endTime,breakHour);
                Workplan workplan = new Workplan(day,workerName);
                workplan.setSchedules(scheduleList);
                workplanService.createWorkplan(workplan);
                // Add workplan entity to workplan list
                workplanList.add(workplan);
            }
            /*Skills*/
            List<Skill> workerSkills = new ArrayList<Skill>();
            // if skill id not null
            if (skills != null || skills.length != 0) {
                for ( String skillId: skills) {
                    // Find each skill with id and add to skill list
                    long id = Long.parseLong(skillId);
                    workerSkills.add(skillService.findSkill(id));
                }
            }

            if (workplanList.size() == 0 ) {
                String messa = "In period from "+beginDate+" to "+endDate+"only weekends";
                request.setAttribute("infoMessage",messa);
                throw new ExceptionCMS(messa, ErrorCode.WORKER_CANNOT_BE_UPDATED);
            } else {
                // Delete old workplans
                for ( Workplan workplan: worker.getWorkplans()) {
                    workplanService.deleteWorkplan(workplan);
                }
            }
            // set all finded skill as user skill
            worker.setName(workerName);
            worker.setTelephone(workerNum);
            worker.setSkills(workerSkills);
            worker.setWorkplans(workplanList);
            workerService.updateWorker(worker);
            LOG.info("Worker "+worker.getName()+ " updated at "+new Date());
            request.setAttribute("sucMessage","Worker \""+worker.getName()+ "\" updated successfully");
        } catch (Exception e) {
            String errMess = ExceptionHandler.handleException(e);
            LOG.info(errMess);
            request.setAttribute("errMessage",errMess);
        }
        request.getRequestDispatcher("/workers").forward(request, response);
    }
}
