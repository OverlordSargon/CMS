package com.zaico.cms.dao.implementation;

import com.zaico.cms.dao.interfaces.UserDAO;
import com.zaico.cms.entities.Order;
import com.zaico.cms.entities.Role;
import com.zaico.cms.entities.User;
import com.zaico.cms.utility.ExceptionCMS;
import org.springframework.stereotype.Component;
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

@Component
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {

    /**
     * Get user by login&password
     */
    @Transactional
    public User userLogin(String login, String password) {
        Query user = em.createNamedQuery("User.login",User.class);
        user.setParameter("login",login);
        user.setParameter("password",password);
        return (User) user.getSingleResult();
    }

    @Transactional
    public List<User> findUserByRole(Role role) throws ExceptionCMS {
        List<User> result = null;
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("User.getByRole");
            List<Role> roleList = new ArrayList<Role>();
            roleList.add(role);
            query.setParameter("roles",roleList);
            result = query.getResultList();
            em.getTransaction().commit();
        }
        catch (Exception exp) {
            em.getTransaction().rollback();
        }
        return result;
    }


    /**
     * Overrited methods for getAll & deleteAll by named queries
     */

}
