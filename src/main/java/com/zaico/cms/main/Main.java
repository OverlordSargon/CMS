package com.zaico.cms.main;


import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.*;
import com.zaico.cms.entities.*;

import com.zaico.cms.utility.ErrorCode;
import com.zaico.cms.utility.ExceptionCMS;
import com.zaico.cms.utility.ExceptionHandler;
import com.zaico.cms.utility.PrintAttributes;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by nzaitsev on 01.08.2016.
 */
public class Main {
    public static void main(String[] args)  throws ExceptionCMS, SQLException, ClassNotFoundException,InterruptedException , IllegalArgumentException,
    IllegalAccessException {
//        OrderDAO orderDAO = FactoryDAO.getOrderDAOInstance();
//        List<Order> allOrders = orderDAO.getAll();
//        String result = "";
//        boolean first = true;
//        try {
//            for (Order order: allOrders) {
//                if ( first == true ) {
//                    PrintAttributes.getHeader(order);
//                    first = false;
//                }
//                result += (PrintAttributes.getAttributes(order));
//            }
//        } catch (Exception e) {
//            result = "FUCK";
//            System.out.println(e);
//        }
//        System.out.println(result);
//        String message ="";
//        String skillName = "skillname";
//        String skillDesc = "skilldesc";
//        try {
//            SkillDAO skillDAO = FactoryDAO.getSkillDAOInstance();
//            Skill newSkill = skillDAO.read(9L);
//            newSkill.setName("Changed mane");
//            skillDAO.update(newSkill);
//            message = "Skill created successfully";
//            System.out.println(message);
//        } catch (Exception e) {
//            ExceptionCMS exc = new ExceptionCMS("Skill not created", ErrorCode.SKILL_CREATE_ERROR);
//            String errorMessage = ExceptionHandler.handleException(exc);
//            System.out.println(errorMessage);
//        }
        int id = 0;
        Object newObj = "9";
        id = Integer.parseInt(newObj.toString());
        System.out.println(id);
    }
}