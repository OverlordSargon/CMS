package com.zaico.cms.main;

import com.zaico.cms.entities.Order;
import com.zaico.cms.entities.Schedule;
import com.zaico.cms.entities.Worker;
import com.zaico.cms.entities.Workplan;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.interfaces.OrderService;
import com.zaico.cms.servicies.interfaces.WorkerService;
import com.zaico.cms.utility.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by nzaitsev on 01.08.2016.
 */
public class Main {
    public static void main(String[] args)  throws ExceptionCMS, SQLException, ClassNotFoundException,InterruptedException , IllegalArgumentException,
            IllegalAccessException, ParseException {
        WorkerService workerService = FactoryService.getWorkerServiceInstance();
        OrderService orderService = FactoryService.getOrderServiceInstance();

        Worker worker = workerService.findWorker(46);

        String fromS = "15:00:00";
        String toS = "17:00:00";
        String dateS = "13-09-2016";

        DateFormat timeF = new SimpleDateFormat("HH:mm");
        DateFormat dateF = new SimpleDateFormat("dd-MM-y");

        Date fromDate = timeF.parse(fromS);
        Date toDate = timeF.parse(toS);
        Date dateDate = dateF.parse(dateS);

        System.out.println(fromDate);
        System.out.println(toDate);
        System.out.println(dateDate);

        Order order = new Order("test","test",dateDate,fromDate,toDate,676,"Test",worker);

        Calendar calFrom = Calendar.getInstance();
        calFrom.setTime(fromDate);

        Calendar calTo = Calendar.getInstance();
        calTo.setTime(toDate);

        Calendar calDate = Calendar.getInstance();
        calDate.setTime(dateDate);

        int intervalFrom = calFrom.get(Calendar.HOUR_OF_DAY)+1;
        int intervalTo = calTo.get(Calendar.HOUR_OF_DAY);


        List<Integer> orderedIntervals = new ArrayList<Integer>();
        for (int i = intervalFrom; i <= intervalTo; i++) {
            orderedIntervals.add(i);
        }

        Calendar calWorkplan = Calendar.getInstance();

        for(Workplan workplan : worker.getWorkplans()) {

            calWorkplan.setTime(workplan.getDate());

            System.out.println(calWorkplan.getTime());
            System.out.println(order.getDate()+" caldate");

            if (calWorkplan.getTime().equals(order.getDate())) {
                for (Integer orderedInterval : orderedIntervals) {
                    System.out.println("for order interval");
                    boolean flag = false;

                    for (Schedule schedule : workplan.getSchedules()) {
                        System.out.println("through schedule");
                        if (schedule.getInterval().equals(orderedInterval) && schedule.getFlag() != "P" ) {
                            System.out.println(workplan.getDate()+" "+schedule.getInterval()+" "+schedule.getFlag() +" set flag W");
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