package com.zaico.cms.dao.implementation;

import com.zaico.cms.dao.interfaces.UserDAO;
import com.zaico.cms.entities.Order;
import com.zaico.cms.entities.Role;
import com.zaico.cms.entities.User;
import com.zaico.cms.utility.ExceptionCMS;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

@Repository("userDao")
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

    public List<User> findUserByRole(Role role) throws ExceptionCMS {
        List<User> result = null;
        Query query = em.createNamedQuery("User.getByRole");
        List<Role> roleList = new ArrayList<Role>();
        roleList.add(role);
        query.setParameter("roles",roleList);
        result = query.getResultList();
        return result;
    }

    public User findByName(String name) throws ExceptionCMS {
        Query user = em.createNamedQuery("User.findByName");
        user.setParameter("username",name);
        return (User) user.getSingleResult();
    }

/**
     * Overrited methods for getAll & deleteAll by named queries
     */

}
