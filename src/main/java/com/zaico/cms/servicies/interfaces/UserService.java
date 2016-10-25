package com.zaico.cms.servicies.interfaces;

import com.zaico.cms.entities.User;
import com.zaico.cms.utility.ExceptionCMS;

import java.util.List;

/**
 * Created by nzaitsev on 17.08.2016.
 */
public interface UserService {

    /**
     * Create new user
     * @param user
     * @return
     * @throws ExceptionCMS
     */
    User createUser(User user) throws ExceptionCMS;

    /**
     * Find user by id
     * @param id
     * @return  User
     * @throws ExceptionCMS
     */
    User findUser(long id) throws ExceptionCMS;

    /**
     * Find all user by entity query
     * @return List of users
     * @throws ExceptionCMS
     */
    List<User> findAllUsers() throws ExceptionCMS;

    /**
     * Find user by name
     * @return  User
     * @throws ExceptionCMS
     */
    User findByName(String name) throws ExceptionCMS;
    /**
     * Update user
     * @param user
     * @return
     * @throws ExceptionCMS
     */
    User updateUser(User user) throws ExceptionCMS;

    /**
     * Delete user
     * @param user
     * @throws ExceptionCMS
     */
    void deleteUser(User user) throws ExceptionCMS;

    /**
     * unset all role
     * @param user
     * @throws ExceptionCMS
     */
    void clearRoles(User user) throws ExceptionCMS;

    /**
     * Login
     */
    User login(String login, String password) throws ExceptionCMS;
}
