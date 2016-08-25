package com.zaico.cms.main;


import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.*;
import com.zaico.cms.entities.*;

import com.zaico.cms.utility.ExceptionCMS;
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
        OrderDAO orderDAO = FactoryDAO.getOrderDAOInstance();
        List<Order> allOrders = orderDAO.getAll();
        String result = "";
        boolean first = true;
        try {
            for (Order order: allOrders) {
                if ( first == true ) {
                    PrintAttributes.getHeader(order);
                    first = false;
                }
                result += (PrintAttributes.getAttributes(order));
            }
        } catch (Exception e) {
            result = "FUCK";
            System.out.println(e);
        }
        System.out.println(result);
    }
}