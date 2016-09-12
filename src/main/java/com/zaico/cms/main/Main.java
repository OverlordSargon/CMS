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

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by nzaitsev on 01.08.2016.
 */
public class Main {
    public static void main(String[] args)  throws ExceptionCMS, SQLException, ClassNotFoundException,InterruptedException , IllegalArgumentException,
            IllegalAccessException, ParseException {
////
//        WorkerService workerService = FactoryService.getWorkerServiceInstance();
//        SkillService skillService = FactoryService.getSkillServiceInstance();
//        WorkplanService workplanService = FactoryService.getWorkplanServiceInstance();
//        ScheduleService scheduleService = FactoryService.getScheduleServiceInstance();
////        Get parameters
//        String workerName = "workhdgfhgfername";
//        int workerNum = Integer.parseInt("5423345");
////      Sample of date
////      Dates for schedule and workplan
//        String beginDate ="12-09-2016 15:00:00";
//        String endDate = "12-09-2016 17:00:00";
//        String[] skills = {"18"};
//        String beginTime = ("10:00:00");
//        String endTime = ("15:00:00");
//        String breakHour = ("13:00:00");
//        EntityManager em = Persistence.createEntityManagerFactory("cms").createEntityManager();
//        DateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-y HH:mm");
//        DateFormat dateF2 = new SimpleDateFormat("dd-MM-y");
//
//
//            /*Find workers by skill*/
////            choose random worker
//            Worker worker = workerService.findWorker(45);
//
//            /*Set flags*/
//            List<Workplan> workplanList = worker.getWorkplans();
//            List<Schedule> scheduleList = new ArrayList<Schedule>();
//            Calendar time = Calendar.getInstance();
//            Calendar wpTime = Calendar.getInstance();
//        Calendar cal2 = Calendar.getInstance();
////            set dates from string
//            Date dateFrom = dateTimeFormat.parse(beginDate);
//            Date dateTo = dateTimeFormat.parse(endDate);
//            Date dgfdgfdgf = dateF2.parse(beginDate);
//            time.setTime(dateFrom);
//        wpTime.setTime(dgfdgfdgf);
//        while(time.getTime().compareTo(dateTo) != 1) {
//                for(Workplan workplan: workplanList) {
//                    cal2.setTime(workplan.getDate());
//                    System.out.println(wpTime.getTime());
//                    System.out.println(workplan.getDate()+"klgnfldnfg");
//                    if(wpTime.equals(cal2) ) {
////                        scheduleList = workplan.getSchedules();
////                        for ( Schedule schedule: scheduleList) {
////                            if ( schedule.getInterval() == time.get(Calendar.HOUR+1)) {
////                                schedule.setFlag("W");
////                                System.out.print(schedule.getInterval());
////                            }
////                        }
//                        System.out.println(":SCUC");
//                    }
//                    time.add(Calendar.HOUR_OF_DAY,1);
//                }
//            }
////            orderService.createOrder(order);
//    }
        Worker worker = new Worker("test", 111, null, null);

        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DAY_OF_MONTH, 2);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DAY_OF_MONTH, 3);
        cal2.set(Calendar.HOUR_OF_DAY, 0);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);

        Workplan workplan1 = new Workplan(cal1.getTime(), "test");
        Workplan workplan2 = new Workplan(cal2.getTime(), "test");

        Schedule schedule11 = new Schedule(1, "F");
        Schedule schedule12 = new Schedule(2, "F");
        Schedule schedule13 = new Schedule(3, "F");
        Schedule schedule14 = new Schedule(4, "F");
        Schedule schedule15 = new Schedule(5, "F");

        Schedule schedule21 = new Schedule(6, "F");
        Schedule schedule22 = new Schedule(7, "F");
        Schedule schedule23 = new Schedule(8, "F");
        Schedule schedule24 = new Schedule(9, "F");
        Schedule schedule25 = new Schedule(10, "F");

        List<Schedule> scheduleList1 = new ArrayList<Schedule>();
        scheduleList1.add(schedule11);
        scheduleList1.add(schedule12);
        scheduleList1.add(schedule13);
        scheduleList1.add(schedule14);
        scheduleList1.add(schedule15);

        List<Schedule> scheduleList2 = new ArrayList<Schedule>();
        scheduleList2.add(schedule21);
        scheduleList2.add(schedule22);
        scheduleList2.add(schedule23);
        scheduleList2.add(schedule24);
        scheduleList2.add(schedule25);

        workplan1.setSchedules(scheduleList1);
        workplan2.setSchedules(scheduleList2);

        List<Workplan> workplanList = new ArrayList<Workplan>();
        workplanList.add(workplan1);
        workplanList.add(workplan2);

        worker.setWorkplans(workplanList);

        Calendar cal3 = Calendar.getInstance();
        cal1.add(Calendar.DAY_OF_MONTH, 3);
        cal3.set(Calendar.HOUR_OF_DAY, 0);
        cal3.set(Calendar.MINUTE, 0);
        cal3.set(Calendar.SECOND, 0);
        cal3.set(Calendar.MILLISECOND, 0);

        Calendar cal4 = Calendar.getInstance();
        cal4.add(Calendar.DAY_OF_MONTH, 3);
        cal4.set(Calendar.HOUR_OF_DAY, 7);

        Calendar cal5 = Calendar.getInstance();
        cal5.add(Calendar.DAY_OF_MONTH, 3);
        cal5.set(Calendar.HOUR_OF_DAY, 9);

        Order order = new Order("111", "test", cal2.getTime(), cal4.getTime(), cal5.getTime(), 123, "test", worker);

        //
        Date date = order.getDate();

        Calendar calFrom = Calendar.getInstance();
        calFrom.setTime(order.getFrom());
        int intervalFrom = calFrom.get(Calendar.HOUR_OF_DAY);

        Calendar calTo = Calendar.getInstance();
        calTo.setTime(order.getTo());
        int intervalTo = calTo.get(Calendar.HOUR_OF_DAY) - 1;

        List<Integer> orderedIntervals = new ArrayList<>();
        for (int i = intervalFrom; i <= intervalTo; i++) {
            orderedIntervals.add(i);
        }

        for(Workplan workplan : worker.getWorkplans()) {

            if (workplan.getDate().equals(date)) {
                for (Integer orderedInterval : orderedIntervals) {
                    System.out.println("for order interval");
                    boolean flag = false;

                    for (Schedule schedule : workplan.getSchedules()) {
                        System.out.println("thrugh schedule");
                        if (schedule.getInterval().equals(orderedInterval)) {
                            System.out.println("set flag");
                            schedule.setFlag("W");
                            flag = true;
                            break;
                        }
                    }

                    if (!flag) {
                        throw new ExceptionCMS("Interval not found.", 0);
                    }
                }
            }
        }
    }
}
