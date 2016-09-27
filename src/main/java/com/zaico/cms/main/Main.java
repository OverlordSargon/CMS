package com.zaico.cms.main;

import com.zaico.cms.entities.Order;
import com.zaico.cms.entities.Schedule;
import com.zaico.cms.entities.Worker;
import com.zaico.cms.entities.Workplan;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.interfaces.OrderService;
import com.zaico.cms.servicies.interfaces.ScheduleService;
import com.zaico.cms.servicies.interfaces.SkillService;
import com.zaico.cms.servicies.interfaces.WorkerService;
import com.zaico.cms.servlets.HelloWorld;
import com.zaico.cms.utility.*;
import org.apache.log4j.Logger;

import java.awt.color.CMMException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by nzaitsev on 01.08.2016.
 */
public class Main {
    public static Logger logger = Logger.getLogger(HelloWorld.class);

    public static void main(String[] args)  throws ExceptionCMS, SQLException, ClassNotFoundException,InterruptedException , IllegalArgumentException,
            IllegalAccessException, ParseException {
        OrderService orderService = FactoryService.getOrderServiceInstance();
        WorkerService workerService = FactoryService.getWorkerServiceInstance();
        SkillService skillService = FactoryService.getSkillServiceInstance();

        String orderNum = "FR2-X";
        String orderDesc = "orderdesc";
        Long orderSkill = 17L;
        String dateS = "21-09-2016";
        String fromS = "10:00:00";
        String toS = "12:00:00";
        String orderClient = "ordercname";
        int orderCleintNum = 123414;

        ScheduleService scheduleService = FactoryService.getScheduleServiceInstance();

        try {
//          string dates into dates
            DateFormat timeF = new SimpleDateFormat("HH:mm");
            DateFormat dateF = new SimpleDateFormat("dd-MM-y");

//          create dates
            Date fromDate = timeF.parse(fromS);
            Date toDate = timeF.parse(toS);
            Date dateDate = dateF.parse(dateS);

//          calendars for all dates
            Calendar calFrom = Calendar.getInstance();
            calFrom.setTime(fromDate);

            Calendar calTo = Calendar.getInstance();
            calTo.setTime(toDate);

            Calendar calDate = Calendar.getInstance();
            calDate.setTime(dateDate);

        /*Find workers by skill*/
            orderService.findCapacity(calDate,calFrom,calTo,orderSkill,"W",null);

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}