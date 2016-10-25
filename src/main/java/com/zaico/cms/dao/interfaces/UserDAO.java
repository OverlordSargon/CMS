package com.zaico.cms.dao.interfaces;

import com.zaico.cms.entities.Role;
import com.zaico.cms.entities.User;
import com.zaico.cms.utility.ExceptionCMS;

import java.util.List;

/**
 * Created by ZAITNIK on 06.08.2016.
 * @author ZAITNIK
 */
public interface UserDAO  extends CommonDAO<User> {
    /**
     * Methods
     */
    User userLogin(String login, String password);

    List<User> findUserByRole(Role role) throws ExceptionCMS;

    User findByName(String name) throws ExceptionCMS;

}
