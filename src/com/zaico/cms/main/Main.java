package com.zaico.cms.main;

import com.zaico.cms.dao.implementation.RoleDAOImpl;
import com.zaico.cms.dao.implementation.SkillDAOImpl;
import com.zaico.cms.dao.implementation.UserDAOImpl;
import com.zaico.cms.dao.interfaces.UserDAO;
import com.zaico.cms.entities.FactoryDAO;
import com.zaico.cms.entities.Role;
import com.zaico.cms.entities.Skill;
import com.zaico.cms.entities.User;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 01.08.2016.
 */
public class Main {
    public static void main(String[] args)  throws SQLException, ClassNotFoundException,InterruptedException {

//        System.out.println(new Date().toString());
//        System.out.println();
//        System.out.println(ZonedDateTime.now());
//        System.out.println("HELllo");
//        User user = new User("Clear","Test2");
        UserDAO ui = new UserDAOImpl();
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
//        Skill skill = new Skill("TestSkill2","TestSkillDesc2");
//        SkillDAOImpl si = new SkillDAOImpl();
//        si.create(skill);
//
//        if (user.getRoles() == null) {
//            user.setRoles(new ArrayList());
//        }
//        user.getRoles().add(role);
//        ui.update(user);
        User user = FactoryDAO.getUserInstance();
        user.setLogin("Gromila");
        user.setPassword("LIZAUT");
        ui.create(user);

        User user2 = FactoryDAO.getUserInstance();
        ui.create(user2);
        user2.setLogin("Няшечка");
        Thread.sleep(8000);
        ui.update(user2);
//        User oldu = ui.read(Long.valueOf(28));
//        oldu.setLogin("Rhacsafsfsafsfasfa");
//        ui.update(oldu);

    }
}
