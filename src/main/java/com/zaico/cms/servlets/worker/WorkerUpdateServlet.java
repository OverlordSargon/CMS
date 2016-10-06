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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-y");

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
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            // Dates for schedule and workplan
            String beginDate = request.getParameter("begindate");
            String endDate = request.getParameter("enddate");
            String beginTime = request.getParameter("begintime");
            String endTime = request.getParameter("endtime");
            String breakHour = request.getParameter("breakhour");
            //Check FROM & TO
            CheckFromTo.checkHours(beginDate,endDate);
            CheckFromTo.checkHours(beginTime,endTime);
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
            Date newBeginDate = dateFormat.parse(beginDate);
            Date newEndDate = dateFormat.parse(endDate);
            // Get first & last W_P
            List<Workplan> workplanEdges = workerService.findEdges(worker);
            Workplan fistWorkplan = workplanEdges.get(0);
            Workplan lastWorkplan = workplanEdges.get(1);
            // UPDATE DATE
            // START FIRST W_P DATE (FIRST DATE)
            // NEW DATE b4 FIRST DATE
            if ( newBeginDate.before(fistWorkplan.getDate()) ) {

                // CREATE NEW W_P
                LOG.debug("new date < old date");
                /* Create new workplan with newbigindate */
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(newBeginDate);
                // UNTIL CALENDAR = FIRST DATE
                while (calendar.getTime().before(fistWorkplan.getDate())) {
                    Timestamp dayDate = new Timestamp(calendar.getTimeInMillis());
                    Workplan workplan = new Workplan(dayDate, worker.getName());
                    workplan.setUpdatedAt(new Date());
                    workplan.setUpdatedAt(new Date());
                    workplan.setSchedules(DaySchedule.scheduleList(beginTime, endTime, breakHour));
                    worker.getWorkplans().add(workplan);
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }
            }

            //NEW DATE AFTER FIST DATE
            if ( newBeginDate.after(fistWorkplan.getDate()) ) {
                LOG.debug("new date > old date");
                /* if date today of already in workplans */
                // check for W flags
                List<Workplan> deleteWorkplanList = new ArrayList<Workplan>();
                for (Workplan workplan : worker.getWorkplans()) {
                    // FIND AND DELETE ALL W_P b4 NEW DATE
                    if (workplan.getDate().before(newBeginDate)) {
                        LOG.debug("workplan " + workplan.getDate() + " after " + newBeginDate);
                        // if date of workplan < newBeginDate
                        checkScheduleFlag(workplan);
                        deleteWorkplanList.add(workplan);
                    }
                }
                // UNSET AND DELETE W_P
                for (Workplan workplan : deleteWorkplanList) {
                    worker.getWorkplans().remove(workplan);
                    workplanService.deleteWorkplan(workplan);
                }
            }
            // END FIST DATE

            // START LAST W_P DATE (LAST DATE)
            // NEW DATE after LAST DATE
            if ( newEndDate.after(lastWorkplan.getDate()) ) {
                // CREATE NEW
                LOG.debug("new date > old date");
                /* Create new workplan with newbigindate */
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(newEndDate);
                // UNTIL CALENDAR = FIRST DATE
                while (calendar.getTime().after(lastWorkplan.getDate())) {
                    String date = calendar.getTime().toString();
                    Date dayDate = calendar.getTime();
                    Workplan workplan = new Workplan(dayDate, worker.getName());
                    workplan.setUpdatedAt(new Date());
                    workplan.setUpdatedAt(new Date());
                    workplan.setSchedules(DaySchedule.scheduleList(beginTime, endTime, breakHour));
                    worker.getWorkplans().add(workplan);
                    calendar.add(Calendar.DAY_OF_MONTH, -1);
                }
            }
            // NEW DATE before LAST DATE
            if ( newEndDate.before(lastWorkplan.getDate())) {
                LOG.debug("new date > old date");
                /* if date today of already in workplans */
                // check for W flags
                List<Workplan> deleteWorkplanList = new ArrayList<Workplan>();
                for (Workplan workplan : worker.getWorkplans()) {
                    if (workplan.getDate().after(newEndDate)) {
                        LOG.debug("workplan " + workplan.getDate() + " after " + newBeginDate);
                        // if date of workplan < newBeginDate
                        checkScheduleFlag(workplan);
                        deleteWorkplanList.add(workplan);
                    }
                }
                // Unset & delete W_P
                for (Workplan workplan : deleteWorkplanList) {
                    worker.getWorkplans().remove(workplan);
                    workplanService.deleteWorkplan(workplan);
                }
            }
            //END LAST DATE
            // set all finded skill as user skill
            worker.setName(workerName);
            worker.setTelephone(workerNum);
            worker.setSkills(workerSkills);
            workerService.updateWorker(worker);
            LOG.info("Worker "+worker.getName()+ " updated at "+new Date());
            request.setAttribute("sucMessage","Worker \""+worker.getName()+ "\" updated successfully");
            request.getRequestDispatcher("/workers").forward(request, response);
        } catch (Exception e) {
            String errMess = ExceptionHandler.handleException(e);
            request.setAttribute("errMessage",errMess);
            doGet(request,response);
        }
    }

    /**
     * Check that schedule , we want to delete without workflags
     * @param workplan
     * @throws ExceptionCMS
     */
    private void checkScheduleFlag(Workplan workplan) throws ExceptionCMS {
        for (Schedule schedule : workplan.getSchedules()) {
            // IF W_P HAS WORK - EXC
            if (schedule.getFlag().equals("W")) {
                String mess = "Worker has orders on "+dateFormat.format(workplan.getDate())+" at "+schedule.getInterval()+":00";
                LOG.error(mess);
                throw new ExceptionCMS(mess,ErrorCode.WORKER_CANNOT_BE_UPDATED);
            }
        }
    }
}
