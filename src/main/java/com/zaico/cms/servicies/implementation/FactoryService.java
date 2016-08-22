package com.zaico.cms.servicies.implementation;

import com.zaico.cms.servicies.interfaces.UserService;

/**
 * Created by nzaitsev on 22.08.2016.
 */
public class FactoryService {

    /**
     * Singleton variables
     */

    /**
     * User
     */
    public static UserService userService;

    /**
     * Singleton methods
     */

    /**
     * Get userservince instance
     * @return userservice implementation
     */
    public static UserService getUserServiceInstance() {
        if ( userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }
}
