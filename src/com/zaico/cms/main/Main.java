package com.zaico.cms.main;

import com.zaico.cms.dao.implementation.UserDAO;
import com.zaico.cms.dao.interfaces.UserInterface;
import com.zaico.cms.entities.User;

/**
 * Created by nzaitsev on 01.08.2016.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("HELllo");
        User user = new User("logidn","password");
        UserInterface ui = new UserDAO();
        ui.create(user);
    }
}
