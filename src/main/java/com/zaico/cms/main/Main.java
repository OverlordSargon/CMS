package com.zaico.cms.main;

import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.WorkerDAO;
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
import org.apache.log4j.LogManager;
import org.apache.log4j.LogManager; import org.apache.log4j.Logger;

import java.awt.color.CMMException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * Created by nzaitsev on 01.08.2016.
 */
public class Main {
    public static Logger logger = LogManager.getLogger(HelloWorld.class);

    public static void main(String[] args)  throws ExceptionCMS, SQLException, ClassNotFoundException,InterruptedException , IllegalArgumentException,
            IllegalAccessException, ParseException {
        OrderService orderService = FactoryService.getOrderServiceInstance();
        WorkerService workerService = FactoryService.getWorkerServiceInstance();
//        SkillService skillService = FactoryService.getSkillServiceInstance();
//        ScheduleService scheduleService = FactoryService.getScheduleServiceInstance();

        String orderNum = "FR2-X";
        String orderDesc = "orderdesc";
        Long orderSkill = 17L;
        String dateS = "30-09-2016";
        String fromS = "18:03:00";
        String toS = "12:00:00";
        String orderClient = "ordercname";
        int orderCleintNum = 123414;


        try {
//          string dates into dates
//            DateFormat timeF = new SimpleDateFormat("HH:mm");
//            DateFormat dateF = new SimpleDateFormat("dd-MM-y");
//            DateFormat fullD = new SimpleDateFormat("HH:mm:ss dd-MM-y");

////          create dates
//            Date fromDate = timeF.parse(fromS);
//            Date toDate = timeF.parse(toS);
//            Date dateDate = dateF.parse(dateS);
//            StringBuffer stringBuffer = new StringBuffer(fromS);
//            stringBuffer.append(" ");
//            stringBuffer.append(dateS);
////            Date fullDate = fullD.parse(stringBuffer.toString());
//
////          calendars for all dates
//            Calendar calFrom = Calendar.getInstance();
//            calFrom.setTime(fromDate);
//
//            Calendar calTo = Calendar.getInstance();
//            calTo.setTime(toDate);
//
//            Calendar calDate = Calendar.getInstance();
//            calDate.setTime(dateDate);
//
//            Calendar orderDate = Calendar.getInstance();
//            orderDate.setTime(fullDate);
//
//            Calendar today = Calendar.getInstance();
//            today.setTime(new Date());

            Worker worker = workerService.findWorker(73);
            Workplan workplan = worker.getWorkplans().get(0);

            List<Workplan> workplanList = workerService.findEdges(worker);
            for ( Workplan workplan1: workplanList) {
                System.out.println(workplan1.getDate());
            }
//            Date fist = workplan.getDate();
//            Date last = worker.getWorkplans().get(worker.getWorkplans().size()-1).getDate();
//
//            Calendar calFirst = Calendar.getInstance();
//            Calendar calLast = Calendar.getInstance();
//
//            DateFormat dateFormat = new SimpleDateFormat("dd-MM-y");
//
//            calFirst.setTime(fist);
//            calLast.setTime(last);
//
//            String dateF = dateFormat.format(fist);
//            String dateL = dateFormat.format(last);
//
//            for (Workplan workplan1: worker.getWorkplans()) {
//                System.out.println(workplan1.getDate());
//                if ( workplan1.getDate().before(new Date())) {
//                    System.out.println(new Date());
//                    System.out.println(dateFormat.format(workplan1.getDate()));
//                    System.out.println("WP date before today");
//                }
//                if (workplan1.getDate().after(new Date())) {
//                    System.out.println("WP DAte after today");
//                }
//                if ( workplan1.getDate().before(calLast.getTime())) {
//                    System.out.println(calLast.getTime());
//                    System.out.println("WP date befor calendar");
//                }
//            }
//
//            SimpleDateFormat ft = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a", Locale.US);
//            java.util.Date t= new Date();
//            System.out.println(t);
//            ft.applyPattern("dd-MM-y");
//            System.out.println(ft.format(t));
////            System.out.println(date);

            String date = "Thu Feb 02 00:00:00 WET 2012";
            SimpleDateFormat formatnow = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.ENGLISH);
            SimpleDateFormat formatneeded=new SimpleDateFormat("YYYY-MM-dd");
            Date date1 = formatnow.parse(date);
            String date2 = formatneeded.format(date1);
            Date date3 = formatneeded.parse(date2);
            System.out.println(date2.toString());
            System.out.println(date3);


//            System.out.println("Order date: "+orderDate.getTime());
//            System.out.println("Today: "+today.getTime());
//            if ( orderDate.getTime().before(today.getTime())) {
//                System.out.println("Wrong time!!!!");
//            } else {
//                System.out.println("Right time!!!!");
//            }
//            WorkerDAO workerDAO = FactoryDAO.getWorkerDAOInstance();
//
//            Worker worker = workerService.findWorker(51);
//            if (orderService.getByWorker(worker).size() == 0) {
//                System.out.println("deletet");
//            } else {
//                System.out.println("catn delete");
//            }

        /*Find workers by skill*/
//            orderService.findCapacity(calDate,calFrom,calTo,orderSkill,"W",null);

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}