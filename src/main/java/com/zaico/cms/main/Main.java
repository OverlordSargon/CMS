package com.zaico.cms.main;

import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.*;
import com.zaico.cms.entities.*;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.UserServiceImpl;
import com.zaico.cms.servicies.interfaces.UserService;
import com.zaico.cms.utility.ErrorCode;
import com.zaico.cms.utility.ExceptionCMS;
import com.zaico.cms.utility.ExceptionHandler;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by nzaitsev on 01.08.2016.
 */
public class Main {
    public static void main(String[] args)  throws ExceptionCMS, SQLException, ClassNotFoundException,InterruptedException {

//        UserServiceImpl usi = new UserServiceImpl();
//        usi.login("0verlord","12");

        try {
            EntityManager em = Persistence.createEntityManagerFactory("cms").createEntityManager();
            User result = null;
            Query user = em.createNamedQuery("User.login",User.class);
            user.setParameter("login","0verlord");
            user.setParameter("password","3");
            result = (User) user.getSingleResult();
        }
        catch (Exception e) {
            String errorMessage = ExceptionHandler.handleException(e);
            System.out.println(errorMessage);
        }

// /        UserService userService = FactoryService.getUserServiceInstance();
//        UserDAO userDAO = FactoryDAO.getUserDAOInstance();
//        User u = userDAO.userLogin("0verlord","1234");
//        try {
//
//            if ( u != null ) {
//                System.out.println("Success");
//                System.out.println(u.getLogin());
//            }
//            else {
//                System.out.println("failed "+u.getLogin());
//            }
//        }
//        catch (Exception e) {
//            System.out.println(e.toString());
//
//        }
//        ScheduleDAO scheduleDAO = FactoryDAO.getScheduleDAOInstance();
//        WorkplanDAO workplanDAO = FactoryDAO.getWorkplanDAOInstance();
//        OrderDAO orderDAO = FactoryDAO.getOrderDAOInstance();
//        OrderDAO orderDAO2 = FactoryDAO.getOrderDAOInstance();
//        WorkerDAO workerDAO = FactoryDAO.getWorkerDAOInstance();
//        Worker work2 = workerDAO.read(3L);
//
//
////        Schedule schedule1 = new Schedule(1,"W");
////        Schedule schedule2 = new Schedule(1,"W");
////        Schedule schedule3 = new Schedule(1,"W");
////        Schedule schedule4 = new Schedule(1,"W");
////        Schedule schedule5 = new Schedule(1,"W");
////        Schedule schedule6 = new Schedule(1,"W");
////
////        List<Schedule> scheduleList1 = new ArrayList<>();
////        List<Schedule> scheduleList2 = new ArrayList<>();
////        scheduleList1.add(schedule1);
////        scheduleList1.add(schedule2);
////        scheduleList1.add(schedule3);
////        scheduleList2.add(schedule4);
////        scheduleList2.add(schedule5);
////        scheduleList2.add(schedule6);
////
////        Workplan workplan1 = new Workplan(new Date(),"TEST");
////        Workplan workplan2 = new Workplan(new Date(),"TEST2");
////        workplan1.setSchedules(scheduleList1);
////        workplan2.setSchedules(scheduleList2);
////
////        List<Workplan> workplanList1 = new ArrayList<>();
////        workplanList1.add(workplan1);
////        workplanList1.add(workplan2);
////
////        Worker worker1 = new Worker("NameTest",1111);
////        worker1.setWorkplans(workplanList1);
////        workerDAO.create(worker1);
//        Order ord2 = new Order("432f","testdesc",
//                new Date(),new Date(),new Date(),451236,"Clinetbane",null);
//        orderDAO.create(ord2);

    }
}