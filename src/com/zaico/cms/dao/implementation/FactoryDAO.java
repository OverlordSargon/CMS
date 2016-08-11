package com.zaico.cms.dao.implementation;

import com.zaico.cms.dao.implementation.*;
import com.zaico.cms.dao.interfaces.*;

/**
 * Created by nzaitsev on 11.08.2016.
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
     * Get order instance
     * @return Order
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
     * Get order instance
     * @return Order
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
     * Get order instance
     * @return Order
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
     * Get order instance
     * @return Order
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
     * Get order instance
     * @return Order
     */
    public static WorkplanDAO getWorkplanDAOInstance() {
        if ( workplanDAOInstance == null) {
            workplanDAOInstance = new WorkplanDAOImpl();
        }
        return workplanDAOInstance;
    }

}
