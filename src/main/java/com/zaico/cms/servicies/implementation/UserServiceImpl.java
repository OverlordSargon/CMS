package com.zaico.cms.servicies.implementation;

import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.UserDAO;
import com.zaico.cms.entities.Role;
import com.zaico.cms.entities.User;
import com.zaico.cms.servicies.interfaces.UserService;
import com.zaico.cms.utility.ErrorCode;
import com.zaico.cms.utility.ExceptionCMS;


import org.apache.log4j.LogManager; import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

/**
 * Created by nzaitsev on 17.08.2016.
 */
@Service("userService")
public class UserServiceImpl implements UserService,UserDetailsService {

    // Logger
    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);
    // DAO
    @Autowired
    private UserDAO userDAO;

    /**
     * Create new User
     * @param user
     * @return
     * @throws ExceptionCMS
     */
    @Transactional
    public User createUser(User user) throws ExceptionCMS {
        try {
            return userDAO.create(user);
        } catch (Exception e) {
            String errMes = "User creation error:"+new Date();
            LOG.error(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.USER_CREATION_ERROR);
        }
    }

    /**
     * Find user be id
     * @param id
     * @return
     * @throws ExceptionCMS
     */
    @Transactional
    public User findUser(long id) throws ExceptionCMS {
        try {
            return userDAO.read(id);
        } catch (Exception e) {
            String errMes = "User not found:"+new Date();
            LOG.error(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.USER_NOT_FOUND);
        }
    }

    /**
     * Find all users
     * @return List of users
     * @throws ExceptionCMS
     */
    @Transactional
    public List<User> findAllUsers() throws ExceptionCMS {
        try {
            return userDAO.getAll();
        } catch (Exception e) {
            String errMes = "ALL USERS ERRRRPOR:"+new Date();
            LOG.error(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.USER_NOT_FOUND);
        }
    }

    /**
     * Find user by name
     * @return User
     * @throws ExceptionCMS
     */
    @Transactional
    public User findByName(String name) throws ExceptionCMS {
        try {
            return userDAO.findByName(name);
        } catch (Exception e) {
            String err = "User "+name+" not found";
            LOG.error(err);
            throw new ExceptionCMS(err,1);
        }
    }

    /**
     * Update user entity
     * @param user
     * @return
     * @throws ExceptionCMS
     */
    @Transactional
    public User updateUser(User user) throws ExceptionCMS {
        try {
            return userDAO.update(user);
        } catch (Exception e) {
            String errMes = "User update error :"+new Date();
            LOG.error(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.USER_CANNOT_BE_UPDATED);
        }
    }

    /**
     * Delete user entity
     * @param user
     * @throws ExceptionCMS
     */
    @Transactional
    public void deleteUser(User user) throws ExceptionCMS {
        try {
            userDAO.delete(user);
        } catch (Exception e) {
            String errMes = "User delete error :"+new Date();
            LOG.error(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.USER_CANNOT_BE_DELETED);
        }
    }

    /**
     * Clear all user roles
     * @param user
     * @throws ExceptionCMS
     */
    @Transactional
    public void clearRoles(User user) throws ExceptionCMS {
        try {
            List<Role> userRoles = new ArrayList<Role>();
            userRoles = null;
            user.setRoles(userRoles);
            userDAO.update(user);
        } catch ( Exception e ) {
            throw new ExceptionCMS("Roles cant be cleared",e);
        }
    }

    /**
     * Get user by login&password
     * @param login
     * @param password
     * @return User
     */
    @Transactional
    public User login(String login, String password) throws ExceptionCMS {
        User result = null;
        try {
            result = userDAO.userLogin(login, password);
        }
        catch (Exception e) {
            String errorMessageLog = "Login error. Login:" + login + " Password: "+password+" : "+new Date();
            LOG.error(errorMessageLog);
            String errorMessage = "Login error. Wrong credentials.";
             throw new ExceptionCMS(errorMessage, ErrorCode.WRONG_CREDENTIALS);
        }
        return result;
    }

    /**
     * Spring authentication method
     */
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = null;
        try {
            user = findByName(username);
        } catch (Exception e ) {

        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        for (Role role : user.getRoles() ) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);

    }
}
