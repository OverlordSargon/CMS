package com.zaico.cms.dao.implementation;

import com.zaico.cms.dao.interfaces.UserInterface;
import com.zaico.cms.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZAITNIK on 08.08.2016.
 */

public class UserDAO extends AbstractDAO<User> implements UserInterface {

    public List<User> getAllUsers(){
        User user = new User();
        List<User> users = new ArrayList<>();
        users.add(user);
        return users;
    }

}
