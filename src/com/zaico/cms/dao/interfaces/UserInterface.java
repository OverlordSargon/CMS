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
    void create();

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
    UserInterface read(Integer id);

    /**
     * Updating userInterface
     * @param userInterface UserInterface entity
     * */
    void update(UserInterface userInterface);

    /**
     * Delete userInterface
     * @param userInterface UserInterface entity
     * */
    void delete(UserInterface userInterface);




}
