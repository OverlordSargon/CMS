package com.zaico.cms.main;

import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.WorkerDAO;
import com.zaico.cms.entities.*;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.interfaces.OrderService;
import com.zaico.cms.servicies.interfaces.ScheduleService;
import com.zaico.cms.servicies.interfaces.SkillService;
import com.zaico.cms.servicies.interfaces.WorkerService;
import com.zaico.cms.utility.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.LogManager; import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataAccessException;

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
    public static Logger logger = LogManager.getLogger(Main.class);

        public static void main(String[] args) {


            String g = "ddd";
            if ( !g.equals(null)) {
                System.out.println("fdsff");
            }
//
//            //Create Spring application context
//
//            //Get service from context. (service's dependency (ProductDAO) is autowired in ProductService)
////            ProductService productService = ctx.getBean(ProductService.class);
//            ApplicationContext context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");
//            SkillService skillService = (SkillService) context.getBean("skillService");
//
//            //Do some data operation
//
////            productService.add(new Product(1, "Bulb"));
////            productService.add(new Product(2, "Dijone mustard"));
////
////            System.out.println("listAll: " + productService.listAll());
//
//            //Test transaction rollback (duplicated key)
//
//            try {
//                Skill skill = skillService.findSkill((long)20);
//                System.out.println(skill);
//                skill.setName("testDoubleSpring");
//                skillService.updateSkill(skill);
//                System.out.println(skill);
////                productService.addAll(Arrays.asList(new Product(3, "Book"), new Product(4, "Soap"), new Product(1, "Computer")));
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//
//            //Test element list after rollback
////            System.out.println("listAll: " + productService.listAll());

        }
    }
