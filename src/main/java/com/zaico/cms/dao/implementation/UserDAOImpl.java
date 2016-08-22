package com.zaico.cms.dao.implementation;

import com.zaico.cms.dao.interfaces.UserDAO;
import com.zaico.cms.entities.User;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZAITNIK on 08.08.2016.
 *
 * @author ZAITNIK
 *         Class for implementation abstract & interfaces
 */

public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {

    /**
     * Get user by login&password
     */
    public User userLogin(String login, String password) {
        Query user = em.createNamedQuery("User.login",User.class);
        user.setParameter("login",login);
        user.setParameter("password",password);
        return (User) user.getSingleResult();
    }
    /**
     * Overrited methods for getAll & deleteAll by named queries
     */

}
