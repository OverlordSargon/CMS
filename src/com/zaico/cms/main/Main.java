package com.zaico.cms.main;

import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.implementation.UserDAOImpl;
import com.zaico.cms.dao.interfaces.UserDAO;
import com.zaico.cms.entities.User;

import java.sql.SQLException;

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
//        Skill skill = new Skill("TestSkill2","TestSkillDesc2");
//        SkillDAOImpl si = new SkillDAOImpl();
//        si.create(skill);
//
//        if (user.getRoles() == null) {
//            user.setRoles(new ArrayList());
//        }
//        user.getRoles().add(role);
//        ui.update(user);
        UserDAO ud1 = FactoryDAO.getUserDAOInstance();
        User user = new User();
        user.setLogin("Gromila");
        user.setPassword("LIZAUT");
        ud1.create(user);

        UserDAO ud2 = FactoryDAO.getUserDAOInstance();
        User user2 = new User();
        ud2.create(user2);
        user2.setLogin("Няшечка");
        user2.setPassword("HERFFFFF");
        Thread.sleep(8000);
        ud2.update(user2);
//        User oldu = ui.read(Long.valueOf(28));
//        oldu.setLogin("Rhacsafsfsafsfasfa");
//        ui.update(oldu);

    }
}
