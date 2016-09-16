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
import com.zaico.cms.utility.*;

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
    public static void main(String[] args)  throws ExceptionCMS, SQLException, ClassNotFoundException,InterruptedException , IllegalArgumentException,
            IllegalAccessException, ParseException {
        OrderService orderService = FactoryService.getOrderServiceInstance();
        WorkerService workerService = FactoryService.getWorkerServiceInstance();
        SkillService skillService = FactoryService.getSkillServiceInstance();

        String orderNum = "FR2-X";
        String orderDesc = "orderdesc";
        Long orderSkill = 17L;
        String dateS = "29-09-2016";
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

//            choose random worker
//          create new order

//          find intervals of work
            int intervalFrom = calFrom.get(Calendar.HOUR_OF_DAY);
            int intervalTo = calTo.get(Calendar.HOUR_OF_DAY);

            List<Integer> orderedIntervals = new ArrayList<Integer>();
            for (int i = intervalFrom; i <= intervalTo; i++) {
                orderedIntervals.add(i);
            }

//            Calendar for workplan date, need to convert correctly
            Calendar calWorkplan = Calendar.getInstance();

            Random random = new Random();
            Worker workerOrder = null;
            Order order = new Order(orderNum, orderDesc, dateDate, fromDate, toDate, orderCleintNum, orderClient, workerOrder);

            List<Worker> workers = workerService.findWorkersBySkill(orderSkill);
            boolean existWorker = false;
            for (Worker worker : workers) {

//            Start of cycle, on all workplans of worker
                for (Workplan workplan : worker.getWorkplans()) {
                    System.out.println("in worker "+worker.getName()+" workplans");

//              set time to calendar for correct comparison
                    calWorkplan.setTime(workplan.getDate());

                    if (calWorkplan.getTime().equals(order.getDate())) {
                        List<Schedule> schedulesWork = new ArrayList<>();
                        System.out.println("in intervals");

                        for (Integer orderedInterval : orderedIntervals) {
                            for (Schedule schedule : workplan.getSchedules()) {
                            /* Create list of right intervals */
                                if (schedule.getInterval().equals(orderedInterval) & schedule.getFlag().equals("F") ) {
                                    System.out.println("right interval"+schedule.getInterval()+schedule.getFlag());
                                    schedulesWork.add(schedule);
                                } else {
                                    System.out.println("wrong interval"+schedule.getInterval());
                                }
                            }
                        }

                        /* If number of booked interval match to number of right schedules */
                        if (orderedIntervals.size() == schedulesWork.size()) {
                            int i = 0;
                            for (Schedule schedule : schedulesWork) {
                                if (schedule.getInterval().equals(orderedIntervals.get(i))) {
                                    schedule.setFlag("W");
                                    i++;
                                    System.out.println("Success update "+schedule.getInterval());
                                }
                            }
                        /* Success creation of schedules */
                            existWorker = true;
                            throw new ExceptionCMS("ALL GOOD",ErrorCode.ORDER_CREATION_ERROR);
                        } else {
                        /* Error between schedules and booking time */
                            throw  new ExceptionCMS("NO SUCH FREE INTERVAL",ErrorCode.ORDER_CREATION_ERROR);
                        }

                    }
                }
            }
            /* if no workers at all */
            if (!existWorker) {
                throw  new ExceptionCMS("NO FREE CAPACITY",ErrorCode.ORDER_CREATION_ERROR);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}