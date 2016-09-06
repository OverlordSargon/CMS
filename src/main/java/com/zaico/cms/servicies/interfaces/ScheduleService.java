package com.zaico.cms.servicies.interfaces;

import com.zaico.cms.entities.Schedule;
import com.zaico.cms.entities.Skill;
import com.zaico.cms.utility.ExceptionCMS;

import java.util.List;

/**
 * Created by nzaitsev on 17.08.2016.
 */
public interface ScheduleService {

    /**
     *
     * @param id
     * @return
     * @throws ExceptionCMS
     */
    Schedule findSchedule(Long id) throws ExceptionCMS;

    /**
     *
     * @param schedule
     * @return
     * @throws ExceptionCMS
     */
    Schedule createSchedule(Schedule schedule) throws ExceptionCMS;

    /**
     * Update schedule
     */
    Schedule updateSchedule(Schedule schedule) throws ExceptionCMS;

    /**
     * Get list of all schedules
     * @return
     * @throws ExceptionCMS
     */
    List<Schedule> findAllSchedules() throws ExceptionCMS;

    /**
     * Delete Schedule
     * @param schedule
     * @throws ExceptionCMS
     */
    void deleteSchedule(Schedule schedule) throws ExceptionCMS;
}
