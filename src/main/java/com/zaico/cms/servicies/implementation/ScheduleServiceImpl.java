package com.zaico.cms.servicies.implementation;

import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.ScheduleDAO;
import com.zaico.cms.dao.interfaces.SkillDAO;
import com.zaico.cms.entities.Schedule;
import com.zaico.cms.entities.Skill;
import com.zaico.cms.servicies.interfaces.ScheduleService;
import com.zaico.cms.utility.ErrorCode;
import com.zaico.cms.utility.ExceptionCMS;


import org.apache.log4j.LogManager; import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 17.08.2016.
 */
public class ScheduleServiceImpl implements ScheduleService {

    // Logger
    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);
    // DAO
    private ScheduleDAO scheduleDAO = FactoryDAO.getScheduleDAOInstance();

    /**
     * Create new schedule
     * @param schedule
     * @return
     * @throws ExceptionCMS
     */
    public Schedule createSchedule(Schedule schedule) throws ExceptionCMS {
        Schedule result = null;
        try {
            result = scheduleDAO.create(schedule);
        } catch (Exception e) {
            String errorMessage = "Schedule not created:"+new Date();
            throw new ExceptionCMS(errorMessage, ErrorCode.SCHEDULE_CREATE_ERROR);
        }
        return  result;
    }

    /**
     * Get schedule by id
     * @param id
     * @return
     * @throws ExceptionCMS
     */
    public Schedule findSchedule(Long id) throws ExceptionCMS {
        Schedule schedule = null;
        try {
            schedule = scheduleDAO.read(id);
        } catch (Exception e) {
            String errorMessage = "Schedule "+schedule.getInterval()+ " not found: "+new Date();
            LOG.info(errorMessage);
            throw new ExceptionCMS(errorMessage, ErrorCode.SCHEDULE_NOT_FOUND);
        }
        return schedule;
    }

    /**
     * Get all schedules
     * @return Schedule list
     * @throws ExceptionCMS
     */
    public List<Schedule> findAllSchedules() throws ExceptionCMS {
        try {
            return scheduleDAO.getAll();
        } catch (Exception e) {
            String errMes = "ALL SCHEDULES ERROR:"+new Date();
            LOG.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.SCHEDULE_NOT_FOUND);
        }
    }

    /**
     * UpdateSchedule method implementation
     */
    /**
     * Update schedule
     * @param schedule
     * @return updated schedule
     * @throws ExceptionCMS
     */
    public Schedule updateSchedule(Schedule schedule) throws ExceptionCMS {
        Schedule upSchedule = null;
        try {
            upSchedule = scheduleDAO.update(schedule);
            LOG.info("DAO: update "+upSchedule.getInterval());
        }
        catch (Exception e) {
            String errorMessage = "Schedule "+schedule.getInterval()+ " updat error "+new Date();
            LOG.info(errorMessage);
            throw new ExceptionCMS(errorMessage, ErrorCode.SCHEDULE_CREATE_ERROR);
        }
        return upSchedule;
    }

    /**
     * Delete schedule
     * @param schedule
     * @throws ExceptionCMS
     */
    public void deleteSchedule(Schedule schedule) throws ExceptionCMS {
        try {
            scheduleDAO.delete(schedule);
            LOG.info("Schedule "+schedule.getInterval()+" deleted at"+new Date());
        } catch (Exception e) {
            String errMess = "Schedule "+schedule.getInterval()+" delete error: "+new Date();
            LOG.info(errMess);
            throw new ExceptionCMS(errMess,ErrorCode.SCHEDULE_CANNOT_BE_DELETED);
        }
    }
}
