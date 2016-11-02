package com.zaico.cms.servicies.implementation;

import com.zaico.cms.controllers.worker.WorkerDates;
import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.WorkerDAO;
import com.zaico.cms.entities.*;
import com.zaico.cms.servicies.interfaces.*;
import com.zaico.cms.utility.*;


import org.apache.log4j.LogManager; import org.apache.log4j.Logger;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by nzaitsev on 17.08.2016.
 */
@Service("workerService")
@Transactional
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    WorkerService workerService;
    @Autowired
    WorkplanService workplanService;

    // Logger
    private static final Logger LOG = LogManager.getLogger(WorkplanService.class);
    // DAO
    @Autowired
    private WorkerDAO workerDAO;
    //Date format for methods
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-y");

    /**
     * Create new Worker
     * @param worker
     * @return Worker entity
     * @throws ExceptionCMS
     */
    public Worker createWorker(Worker worker) throws ExceptionCMS {
        try {
            return workerDAO.create(worker);
        } catch (Exception e) {
            String errMes = "Worker creation error:"+new Date();
            LOG.info(errMes);
            throw new ExceptionCMS(errMes, ErrorCode.WORKER_CREATION_ERROR);
        }
    }

    /**
     * Find worker be id
     * @param id
     * @return
     * @throws ExceptionCMS
     */
    public Worker findWorker(long id) throws ExceptionCMS {
        try {
            return workerDAO.read(id);
        } catch (Exception e) {
            String errMes = "Worker not found:"+new Date();
            LOG.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.WORKER_NOT_FOUND);
        }
    }

    /**
     * find all workers
     * @return
     * @throws ExceptionCMS
     */
    public List<Worker> findAllWorkers() throws ExceptionCMS {
        try {
            return workerDAO.getAll();
        } catch (Exception e) {
            String errMes = "ALL WORKERS ERROR:"+new Date();
            LOG.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.WORKER_NOT_FOUND);
        }
    }

    /**
     * Get workers by skill id
     * @param skillId
     * @return List of workers
     * @throws ExceptionCMS
     */
    public List<Worker> findWorkersBySkill(long skillId) throws ExceptionCMS {
        try {
            return workerDAO.getBySkill(skillId);
        } catch (Exception e) {
            String errMes = "Find worker by skill error:"+new Date();
            LOG.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.WORKER_NOT_FOUND_BY_SKILL);
        }
    }



    /**
     * Update worker entity
     * @param worker
     * @return
     * @throws ExceptionCMS
     */
    public Worker updateWorker(Worker worker) throws ExceptionCMS {
        try {
            return workerDAO.update(worker);
        } catch (Exception e) {
            String errMes = "Worker update error :"+new Date();
            LOG.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.WORKER_CANNOT_BE_UPDATED);
        }
    }

    /**
     * Delete worker entity
     * @param worker
     * @throws ExceptionCMS
     */
    public void deleteWorker(Worker worker) throws ExceptionCMS {
        OrderService orderService = FactoryService.getOrderServiceInstance();
        try {
            if (orderService.getByWorker(worker).size() == 0) {
                workerDAO.delete(worker);
            } else {
                String err = "This worker haven`t done his work!";
                throw new ExceptionCMS(err,ErrorCode.WORKER_CANNOT_BE_DELETED);
            }
        } catch (Exception e) {
            String errMes = ExceptionHandler.handleException(e);
            LOG.info("DELETE ERROR "+errMes);
            throw new ExceptionCMS(errMes,ErrorCode.WORKER_CANNOT_BE_DELETED);
        }
    }

    /**
     * Get info about worker`s schedule
     * @param worker Worker object
     */

    public WorkerDates findWorkTime(Worker worker) throws ExceptionCMS {

        List<Workplan> edges =  findEdges(worker);
        Workplan workplan = edges.get(0);
        Date fist = workplan.getDate();
        Date last = edges.get(1).getDate();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-y");
        String dateFrom = dateFormat.format(fist);
        String dateTo = dateFormat.format(last);

        int firstHour = workplan.getSchedules().get(0).getInterval();
        int lasstHour = workplan.getSchedules().get(workplan.getSchedules().size()-1).getInterval()+1;
        int pauseHour = 0;
        for (Schedule schedule: workplan.getSchedules()) {
            if (schedule.getFlag().equals("P")) {
                pauseHour = schedule.getInterval();
            }
        }
        WorkerDates workerDates = new WorkerDates();
        workerDates.setBegindate(dateFrom);
        workerDates.setEnddate(dateTo);
        workerDates.setBeginhour(Integer.toString(firstHour));
        workerDates.setEndhour(Integer.toString(lasstHour));
        workerDates.setBreakstart(Integer.toString(pauseHour));
        return workerDates;
    }
    /**
     * Get fist and last workplans
     * @param worker
     * @return List of two workplans
     * @throws ExceptionCMS
     */
    public List<Workplan> findEdges(Worker worker) throws ExceptionCMS {
        List<Workplan> workplanList = worker.getWorkplans();
        WorkplanComparator workplanComparator = new WorkplanComparator();
        Collections.sort(workplanList,workplanComparator);
        List<Workplan> result = new ArrayList<Workplan>();
        result.add(workplanList.get(0));
        result.add(workplanList.get(workplanList.size()-1));
        return  result;
    }

    /**
     * Updates workplan for current worker
     * @param worker
     * @param workerDates
     * @return Worker with updated workplans
     * @throws ExceptionCMS
     */
    public Worker updateWorkplans(Worker worker, WorkerDates workerDates) throws ExceptionCMS {

        try {
            String beginDate = workerDates.getBegindate();
            String endDate = workerDates.getEnddate();
            String beginTime = workerDates.getBeginhour();
            String endTime = workerDates.getEndhour();
            String breakHour = workerDates.getBreakstart();
            //Check FROM & TO
            CheckFromTo.checkDays(beginDate, endDate);
            CheckFromTo.checkHours(beginTime, endTime);

            Date newBeginDate = dateFormat.parse(beginDate);
            Date newEndDate = dateFormat.parse(endDate);
            // Get first & last W_P
            List<Workplan> workplanEdges = workerService.findEdges(worker);
            Workplan fistWorkplan = workplanEdges.get(0);
            Workplan lastWorkplan = workplanEdges.get(1);
            // UPDATE DATE
            // START FIRST W_P DATE (FIRST DATE)
            // NEW DATE b4 FIRST DATE
            if (newBeginDate.before(fistWorkplan.getDate())) {

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
            if (newBeginDate.after(fistWorkplan.getDate())) {
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
            if (newEndDate.after(lastWorkplan.getDate())) {
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
            if (newEndDate.before(lastWorkplan.getDate())) {
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
                //END LAST DATE
            }
        } catch (Exception e) {
            throw new ExceptionCMS("Worker update workplnas error",ErrorCode.WORKER_CANNOT_BE_UPDATED);
        }
        return worker;
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
    @Autowired
    SkillService skillService;

    public Worker setSkillsFromForm(Worker worker) throws ExceptionCMS {
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
        worker.setSkills(workerSkills);
        return worker;
    }

}
