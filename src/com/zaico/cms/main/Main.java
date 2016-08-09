package com.zaico.cms.main;

import com.zaico.cms.dao.implementation.UserDAO;
import com.zaico.cms.dao.interfaces.UserInterface;
import com.zaico.cms.entities.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by nzaitsev on 01.08.2016.
 */
public class Main {
    public static void main(String[] args)  throws SQLException, ClassNotFoundException {

        System.out.println("HELllo");
//        User user = new User("Никита","Ybrbneirf");
//        UserInterface userIn = new UserDAO();
//        userIn.create(user);
        Class.forName("com.mysql.jdbc.Driver.java");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms","root", "1234");
        conn.close();
    }
}
