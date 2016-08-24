package com.zaico.cms.servicies.implementation;

import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.UserDAO;
import com.zaico.cms.entities.User;
import com.zaico.cms.servicies.interfaces.UserService;
import com.zaico.cms.utility.ErrorCode;
import com.zaico.cms.utility.ExceptionCMS;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by nzaitsev on 17.08.2016.
 */
public class UserServiceImpl implements UserService {

    /**
     *
     */
    private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);

    /**
     * Get user by login&password
     * @param login
     * @param password
     * @return User
     */
    public User login(String login, String password) throws ExceptionCMS {
        User result = null;
        UserDAO userDAO = FactoryDAO.getUserDAOInstance();
        try {
            result = userDAO.userLogin(login, password);
        }
        catch (Exception e) {
                String errorMessage = "User with login " + login + " not found";
                LOG.info(errorMessage);
                throw new ExceptionCMS(errorMessage, ErrorCode.USER_NOT_FOUND);
        }
        return result;
    }
}
