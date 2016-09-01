package com.zaico.cms.servicies.implementation;

import com.zaico.cms.servicies.interfaces.SkillService;
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
    private static UserService userService;

    /**
     * Skill
     */
    private static SkillService skillService;

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
    
    /**
     * Get skillservince instance
     * @return skillservice implementation
     */
    public static SkillService getSkillServiceInstance() {
        if ( skillService == null) {
            skillService = new SkillServiceImpl();
        }
        return skillService;
    }
}
