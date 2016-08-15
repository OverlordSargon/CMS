package com.zaico.cms.main;

import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.implementation.SkillDAOImpl;
import com.zaico.cms.dao.implementation.UserDAOImpl;
import com.zaico.cms.dao.interfaces.*;
import com.zaico.cms.entities.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 01.08.2016.
 */
public class Main {
    public static void main(String[] args)  throws Exception, SQLException, ClassNotFoundException,InterruptedException {

//        System.out.println(new Date().toString());
//        System.out.println();
//        System.out.println(ZonedDateTime.now());
//        System.out.println("HELllo");
//        User user = new User("Clear","Test2");
//        UserDAO ui = new UserDAOImpl();
//        ui.deleteAll();
//        ui.create(user);
//        user.setLogin("TestUpdate");
//
//        Role role = new Role("TestRole","TestRoleDesc");
//        RoleDAOImpl ri = new RoleDAOImpl();
//        ri.create(role);
//
////      Check existing user roles, to avoid EXEPT
//
        /* DAO */
        SkillDAO si = FactoryDAO.getSkillDAOInstance();
        WorkerDAO wd = FactoryDAO.getWorkerDAOInstance();
        WorkplanDAO wpd = FactoryDAO.getWorkplanDAOInstance();
        OrderDAO ordd = FactoryDAO.getOrderDAOInstance();
        ScheduleDAO schD = FactoryDAO.getScheduleDAOInstance();

        /* Clear */
        schD.deleteAll();
        wpd.deleteAll();
        si.deleteAll();
        wd.deleteAll();
        ordd.deleteAll();

        /* Operations */

        Skill skill = new Skill("Установка интернета","Описание установки интернета");
        List<Skill> newskills = new ArrayList<Skill>();
        si.create(skill);
        newskills.add(skill);

        Worker worker1 = new Worker("WONG",8434);
        worker1.setSkills(newskills);
        wd.create(worker1);

        Workplan wp1 = new Workplan(new Date(),worker1,"Description to worker WONG");
        wpd.create(wp1);

        for(int i = 10; i < 19; i++) {
            Schedule sch = new Schedule();
            sch.setWorkplan(wp1);
            sch.setInterval(i);

            if ( i == 14) {
                sch.setFlag("P");
            }
            else sch.setFlag("F");
            schD.create(sch);
        }


//
//        DateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
//        String ds = "31-08-2016 10:20:56";
//        String de = "1-09-2016 10:20:56";
//        Date dateS = sdf.parse(ds);
//        Date dateE = sdf.parse(de);

//        Order order1 = new Order("F1","Install Inet",new Date(),ds,de,5665,"Vasya",worker1);
//        /* Saving in DB */
//
//        if (user.getRoles() == null) {
//            user.setRoles(new ArrayList());
//        }
//        user.getRoles().add(role);
//        ui.update(user);
//        UserDAO ud1 = FactoryDAO.getUserDAOInstance();
//        User user = new User();
//        user.setLogin("Gromila");
//        user.setPassword("LIZAUT");
//        ud1.create(user);
//
//        UserDAO ud2 = FactoryDAO.getUserDAOInstance();
//        User user2 = new User();
//        ud2.create(user2);
//        user2.setLogin("Няшечка");
//        user2.setPassword("HERFFFFF");
//        Thread.sleep(8000);
//        ud2.update(user2);
//        User oldu = ui.read(Long.valueOf(28));
//        oldu.setLogin("Rhacsafsfsafsfasfa");
//        ui.update(oldu);

    }
}
