package com.zaico.cms.dao.interfaces;

import com.zaico.cms.entities.User;

import java.util.List;

/**
 * Created by ZAITNIK on 06.08.2016.
 * @author ZAITNIK
 */
public interface UserInterface {
    /**
     * Methods
     * */

    /**
     * Allows to create new user
     * */
    User create(User user);

    /**
     * Allows to get all users
     * @return users
     * */
    List<User> getAllUsers();

    /**
     * Get user by id
     * @param id Needed user id
     * @return UserInterface
     * */
    User read(Long id);

    /**
     * Updating userInterface
     * @param user User entity
     * */
    void update(User user);

    /**
     * Delete user
     * @param user user entity
     * */
    void delete(User user);




}
