package com.zaico.cms.servicies.implementation;

import com.mysql.jdbc.log.Log;
import com.mysql.jdbc.log.LogFactory;
import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.OrderDAO;
import com.zaico.cms.dao.interfaces.WorkerDAO;
import com.zaico.cms.entities.Order;
import com.zaico.cms.entities.Worker;
import com.zaico.cms.servicies.interfaces.OrderService;
import com.zaico.cms.utility.ErrorCode;
import com.zaico.cms.utility.ExceptionCMS;

import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 17.08.2016.
 */
public class OrderServiceImpl implements OrderService {

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(UserServiceImpl.class);
    private OrderDAO orderDAO = FactoryDAO.getOrderDAOInstance();

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
            LOG.info(errMes);
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
            LOG.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.ORDER_NOT_FOUND);
        }
    }

    public List<Order> findAllOrders() throws ExceptionCMS {
        try {
            return orderDAO.getAll();
        } catch (Exception e) {
            String errMes = "ALL ORDERS ERROR:"+new Date();
            LOG.info(errMes);
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
            LOG.info(errMes);
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
            LOG.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.ORDER_CANNOT_BE_DELETED);
        }
    }
    
}
