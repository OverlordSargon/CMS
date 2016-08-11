package com.zaico.cms.entities;

/**
 * Created by nzaitsev on 11.08.2016.
 * Singleton factory
 * @author ZAITNIK
 */
public class FactoryDAO {

    /**
     * User singleton
     */
    private static User userInstance;
    /**
     *Get user instance. If not created yet - create, else - return existing
     * @return User
     */
    public static User getUserInstance() {
        if ( userInstance == null) {
            userInstance = new User();
        }
        return userInstance;
    }


    /**
     * Order singleton
     */
    private static Order orderInstance;
    /**
     * Get order instance
     * @return Order
      */
    public static Order getOrderInstance() {
        if ( orderInstance == null) {
            orderInstance = new Order();
        }
        return orderInstance;
    }

    /**
     * Role singleton
     */
    private static Role roleInstance;
    /**
     * Get order instance
     * @return Order
     */
    public static Role getRoleInstance() {
        if ( roleInstance == null) {
            roleInstance = new Role();
        }
        return roleInstance;
    }

    /**
     * Schedule singleton
     */
    private static Schedule scheduleInstance;
    /**
     * Get order instance
     * @return Order
     */
    public static Schedule getScheduleInstance() {
        if ( scheduleInstance == null) {
            scheduleInstance = new Schedule();
        }
        return scheduleInstance;
    }

    /**
     * Skill singleton
     */
    private static Skill skillInstance;
    /**
     * Get order instance
     * @return Order
     */
    public static Skill getSkillInstance() {
        if ( skillInstance == null) {
            skillInstance = new Skill();
        }
        return skillInstance;
    }

    /**
     * Worker singleton
     */
    private static Worker workerInstance;
    /**
     * Get order instance
     * @return Order
     */
    public static Worker getWorkerInstance() {
        if ( workerInstance == null) {
            workerInstance = new Worker();
        }
        return workerInstance;
    }

    /**
     * Workplan singleton
     */
    private static Workplan workplanInstance;
    /**
     * Get order instance
     * @return Order
     */
    public static Workplan getWorkplanInstance() {
        if ( workplanInstance == null) {
            workplanInstance = new Workplan();
        }
        return workplanInstance;
    }

}
