package com.zaico.cms.servicies.implementation;

import com.zaico.cms.servicies.interfaces.*;

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
     * Role
     */
    private static RoleService roleService;

    /**
     * Worker
     */
    private static WorkerService workerService;

    /**
     * Order
     */
    private static OrderService orderService;

    /**
     * Workplan
     */
    private static WorkplanService workplanService;

    /**
     * Schedule
     */
    private static ScheduleService scheduleService;



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

    /**
     * Get role service instance
     * @return skill service implementation
     */
    public static RoleService getRoleServiceInstance() {
        if ( roleService == null) {
            roleService = new RoleServiceImpl();
        }
        return roleService;
    }
    
    /**
     * Get worker service instance
     * @return skill service implementation
     */
    public static WorkerService getWorkerServiceInstance() {
        if ( workerService == null) {
            workerService = new WorkerServiceImpl();
        }
        return workerService;
    }
    
    /**
     * Get order service instance
     * @return skill service implementation
     */
    public static OrderService getOrderServiceInstance() {
        if ( orderService == null) {
            orderService = new OrderServiceImpl();
        }
        return orderService;
    }

    /**
     * Get workplan service instance
     * @return workplan service implementation
     */
    public static WorkplanService getWorkplanServiceInstance() {
        if ( workplanService == null) {
            workplanService = new WorkplanServiceImpl();
        }
        return workplanService;
    }

    /**
     * Get schedule service instance
     * @return schedule service implementation
     */
    public static ScheduleService getScheduleServiceInstance() {
        if ( scheduleService == null) {
            scheduleService = new ScheduleServiceImpl();
        }
        return scheduleService;
    }
}
