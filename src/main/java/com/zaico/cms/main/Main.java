package com.zaico.cms.main;


import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.*;
import com.zaico.cms.entities.*;

import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.UserServiceImpl;
import com.zaico.cms.servicies.interfaces.*;
import com.zaico.cms.utility.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by nzaitsev on 01.08.2016.
 */
public class Main {
    public static void main(String[] args)  throws ExceptionCMS, SQLException, ClassNotFoundException,InterruptedException , IllegalArgumentException,
    IllegalAccessException {
//
        WorkerService workerService = FactoryService.getWorkerServiceInstance();
        SkillService skillService = FactoryService.getSkillServiceInstance();
        WorkplanService workplanService = FactoryService.getWorkplanServiceInstance();
        ScheduleService scheduleService = FactoryService.getScheduleServiceInstance();
//        Get parameters
        String workerName = "workhdgfhgfername";
        int workerNum = Integer.parseInt("5423345");
//      Sample of date
//      Dates for schedule and workplan
        String beginDate ="10-09-2016";
        String endDate = "14-10-2016";
        String[] skills = {"18"};


        try {
//            Empty list of workplans
            List<Workplan> workplanList = new ArrayList<Workplan>();
//            Get list of dates & create workplan for these days
            List<Date> workDays = WorkWeek.getWorkDays(beginDate, endDate);
            ;
            for (Date day : workDays) {
                Workplan workplan = new Workplan(day, workerName);
                workplanService.createWorkplan(workplan);
//                Add workplan entity to workplan list
                workplanList.add(workplan);
            }
            List<Skill> workerSkills = new ArrayList<Skill>();
//            if skill id not null
            if (skills != null && skills.length != 0) {
                for (String skillId : skills) {
//                    Find each skill with id and add to skill list
                    long id = Long.parseLong(skillId);
                    workerSkills.add(skillService.findSkill(id));
                }
            }
//            set all finded skill as user skill
            Worker worker = new Worker(workerName, workerNum);
            workerService.createWorker(worker);
            worker.setSkills(workerSkills);
            worker.setWorkplans(workplanList);
            workerService.updateWorker(worker);
            String message = "Worker \"" + workerName + "\" created at " + new Date();
            System.out.println(message);
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}