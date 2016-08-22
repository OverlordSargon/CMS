package com.zaico.cms.servicies.implementation;

import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.implementation.UserDAOImpl;
import com.zaico.cms.dao.interfaces.UserDAO;
import com.zaico.cms.entities.User;
import com.zaico.cms.servicies.interfaces.CommonService;
import com.zaico.cms.servicies.interfaces.UserService;

import java.util.List;

/**
 * Created by nzaitsev on 17.08.2016.
 */
public class UserServiceImpl implements UserService {

    /**
     * Get yoser bu login&password
     * @param login
     * @param password
     * @return User
     */
    public User login(String login, String password) {
        User result = null;
        UserDAO userDAO = FactoryDAO.getUserDAOInstance();
        try {
            result = userDAO.userLogin(login, password);
        }
        catch (Exception e) {
            
        }
        return result;
    }
}
