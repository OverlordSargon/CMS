package com.zaico.cms.servicies.implementation;


import com.zaico.cms.controllers.order.OrderDates;
import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.OrderDAO;
import com.zaico.cms.dao.interfaces.WorkerDAO;
import com.zaico.cms.entities.*;
import com.zaico.cms.servicies.interfaces.OrderService;
import com.zaico.cms.servicies.interfaces.ScheduleService;
import com.zaico.cms.servicies.interfaces.WorkerService;
import com.zaico.cms.utility.ErrorCode;
import com.zaico.cms.utility.ExceptionCMS;
import org.apache.log4j.LogManager;
import org.apache.log4j.LogManager; import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by nzaitsev on 17.08.2016.
 */
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    // Logger
    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);
    // DAO
    @Autowired
    private OrderDAO orderDAO ;

    @Autowired
    WorkerService workerService;
    @Autowired
    ScheduleService scheduleService;
    /**
     * Create new Order
     * @param order
     * @return Order entity
     * @throws ExceptionCMS
     */
    public Order createOrder(Order order) throws ExceptionCMS {
        try {
            return orderDAO.create(order);
        } catch (Exception e) {
            String errMes = "Order creation error:"+new Date();
            logger.info(errMes);
            throw new ExceptionCMS(errMes, ErrorCode.ORDER_CREATION_ERROR);
        }
    }

    /**
     * Find order be id
     * @param id
     * @return
     * @throws ExceptionCMS
     */
    public Order findOrder(long id) throws ExceptionCMS {
        try {
            return orderDAO.read(id);
        } catch (Exception e) {
            String errMes = "Order not found:"+new Date();
            logger.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.ORDER_NOT_FOUND);
        }
    }

    /**
     * Get all orders
     * @return List of all orders
     * @throws ExceptionCMS
     */
    public List<Order> findAllOrders() throws ExceptionCMS {
        try {
            return orderDAO.getAll();
        } catch (Exception e) {
            String errMes = "ALL ORDERS ERROR:"+new Date();
            logger.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.ORDER_NOT_FOUND);
        }
    }

    /**
     * Update order entity
     * @param order
     * @return
     * @throws ExceptionCMS
     */
    public Order updateOrder(Order order) throws ExceptionCMS {
        try {
            return orderDAO.update(order);
        } catch (Exception e) {
            String errMes = "Order update error :"+new Date();
            logger.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.ORDER_CANNOT_BE_UPDATED);
        }
    }

    /**
     * Delete order entity
     * @param order
     * @throws ExceptionCMS
     */
    public void deleteOrder(Order order) throws ExceptionCMS {
        try {
            orderDAO.delete(order);
        } catch (Exception e) {
            String errMes = "Order delete error :"+new Date();
            logger.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.ORDER_CANNOT_BE_DELETED);
        }
    }


    /**
     * find free capacity. if true - `ll create order in sevlet
     * @param day
     * @param timeFrom
     * @param timeTo
     * @param skill
     * @return  capacityExists
     * @throws ExceptionCMS
     */
    public Worker findCapacity(Calendar day, Calendar timeFrom, Calendar timeTo, Long skill,String flag,Worker existingWorker) throws ExceptionCMS {
        logger.debug("START: find capacity and booking intervals");
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        now.set(Calendar.HOUR_OF_DAY,0);
        now.set(Calendar.MINUTE,0);
        now.set(Calendar.SECOND,0);
        if( day.before(now)) {
            throw  new ExceptionCMS("You choose a paste date",ErrorCode.DATE_BEFORE_TODAY);
        }

        Worker orderWorker = null;

        /*Find workers by skill*/
        //choose random worker
        //create new order
        //find intervals of work
        int intervalFrom = timeFrom.get(Calendar.HOUR_OF_DAY);
        int intervalTo = timeTo.get(Calendar.HOUR_OF_DAY);

        List<Integer> orderedIntervals = new ArrayList<Integer>();
        for (int i = intervalFrom; i < intervalTo; i++) {
            orderedIntervals.add(i);
        }

        //Calendar for workplan date, need to convert correctly
        Calendar calWorkplan = Calendar.getInstance();
        /* handling flags */
        String oldFlag = "";
        if ( flag.equals("W") ) {
            oldFlag = "F";
        } else {
            oldFlag = "W";
        }

        List<Worker> workers = new ArrayList<Worker>();
        if (existingWorker == null ) {
            workers  = workerService.findWorkersBySkill(skill);
        } else {
            workers.add(existingWorker);
        }
        boolean findAtLeastOneInterval = false;
        for (Worker worker : workers) {
        //if at least one interval not found
            if ( !findAtLeastOneInterval ) {
                //Start of cycle, on all workplans of worker
                for (Workplan workplan : worker.getWorkplans()) {
                    //set time to calendar for correct comparison
                    calWorkplan.setTime(workplan.getDate());
                    if (calWorkplan.getTime().equals(day.getTime())) {
                        List<Schedule> schedulesWork = new ArrayList<Schedule>();
                        for (Integer orderedInterval : orderedIntervals) {
                            for (Schedule schedule : workplan.getSchedules()) {
                                /* Create list of right intervals */
                                if (schedule.getInterval().equals(orderedInterval) && (schedule.getFlag().equals(oldFlag) && !schedule.getFlag().equals("P")) ) {
                                    schedulesWork.add(schedule);
                                    findAtLeastOneInterval = true;
                                }
                            }
                        }
                        /* If number of booked interval match to number of right schedules */
                        if (orderedIntervals.size() == schedulesWork.size()) {
                            logger.info("Matched worker " + worker.getName());
                            int i = 0;
                            for (Schedule schedule : schedulesWork) {
                                if (schedule.getInterval().equals(orderedIntervals.get(i))) {
                                    scheduleService.updateFlag(schedule,flag);
                                    i++;
                                    logger.info("Update " + schedule.getInterval()+" interval");
                                }
                            }
                            /* Success creation of schedules */
                            orderWorker = worker;
                            logger.info("Successful booking ");
                            break;
                        } else {
                            throw new ExceptionCMS("No free capacity",ErrorCode.NO_REQUIRED_INTERVAL);
                        }
                    }
                }
            }
        }
        if (!findAtLeastOneInterval) {
            logger.error("END: booking ERROR");
            throw  new ExceptionCMS("NO FREE CAPACITY",ErrorCode.ORDER_CREATION_ERROR);
        }
        logger.info("END: find capacity and booking intervals");
        return orderWorker;
    }

    /**
     *  Get all orders of worker
     * @param worker
     * @return Order list
     * @throws ExceptionCMS
     */
    public List<Order> getByWorker(Worker worker) throws ExceptionCMS {
        List<Order> result = null;
        try {
            result = orderDAO.getByWorker(worker);

        } catch (Exception e) {
            String errMes = "Error with worker orders";
            logger.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.CANT_GET_WORKERs_ORDERS);
        }
        return result;
    }
}
