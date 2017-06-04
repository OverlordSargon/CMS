package com.zaico.cms.dao.implementation;

import com.zaico.cms.dao.implementation.*;
import com.zaico.cms.dao.interfaces.*;

/**
 * Singleton factory
 * @author ZAITNIK
 */
public class FactoryDAO {

    /**
     * UserDAO singleton
     */
    private static UserDAO userDAOInstance;
    /**
     *Get userDAO instance. If not created yet - create, else - return existing
     * @return UserDAO
     */
    public static UserDAO getUserDAOInstance() {
        if ( userDAOInstance == null) {
            userDAOInstance = new UserDAOImpl();
        }
        return userDAOInstance;
    }


    /**
     * OrderDAO singleton
     */
    private static OrderDAO orderDAOInstance;
    /**
     * Get orderDAO instance
     * @return OrderDAO
      */
    public static OrderDAO getOrderDAOInstance() {
        if ( orderDAOInstance == null) {
            orderDAOInstance = new OrderDAOImpl();
        }
        return orderDAOInstance;
    }

    /**
     * RoleDAO singleton
     */
    private static RoleDAO roleDAOInstance;
    /**
     * Get role instance
     * @return Role
     */
    public static RoleDAO getRoleDAOInstance() {
        if ( roleDAOInstance == null) {
            roleDAOInstance = new RoleDAOImpl();
        }
        return roleDAOInstance;
    }

    /**
     * ScheduleDAO singleton
     */
    private static ScheduleDAO scheduleDAOInstance;
    /**
     * Get Schedule instance
     * @return Schedule
     */
    public static ScheduleDAO getScheduleDAOInstance() {
        if ( scheduleDAOInstance == null) {
            scheduleDAOInstance = new ScheduleDAOImpl();
        }
        return scheduleDAOInstance;
    }

    /**
     * SkillDAO singleton
     */
    private static SkillDAO skillDAOInstance;
    /**
     * Get Skill instance
     * @return skill
     */
    public static SkillDAO getSkillDAOInstance() {
        if ( skillDAOInstance == null) {
            skillDAOInstance = new SkillDAOImpl();
        }
        return skillDAOInstance;
    }

    /**
     * WorkerDAO singleton
     */
    private static WorkerDAO workerDAOInstance;
    /**
     * Get worker instance
     * @return Worker
     */
    public static WorkerDAO getWorkerDAOInstance() {
        if ( workerDAOInstance == null) {
            workerDAOInstance = new WorkerDAOImpl();
        }
        return workerDAOInstance;
    }

    /**
     * WorkplanDAO singleton
     */
    private static WorkplanDAO workplanDAOInstance;
    /**
     * Get workplan instance
     * @return Workplan
     */
    public static WorkplanDAO getWorkplanDAOInstance() {
        if ( workplanDAOInstance == null) {
            workplanDAOInstance = new WorkplanDAOImpl();
        }
        return workplanDAOInstance;
    }

}
