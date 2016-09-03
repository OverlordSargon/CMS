package com.zaico.cms.servicies.interfaces;

import com.zaico.cms.entities.Order;
import com.zaico.cms.entities.Worker;
import com.zaico.cms.utility.ExceptionCMS;

import java.util.List;

/**
 * Created by nzaitsev on 17.08.2016.
 */
public interface OrderService {
    /**
     * Create new order
     * @param order
     * @return
     * @throws ExceptionCMS
     */
    Order createOrder(Order order) throws ExceptionCMS;

    /**
     * Find order by id
     * @param id
     * @return
     * @throws ExceptionCMS
     */
    Order findOrder(long id) throws ExceptionCMS;

    /**
     * Find all order
     * @return List of order
     * @throws ExceptionCMS
     */
    List<Order> findAllOrders() throws ExceptionCMS;

    /**
     * Update order entity
     * @param order
     * @return updated order
     * @throws ExceptionCMS
     */
    Order updateOrder(Order order) throws ExceptionCMS;

    /**
     * Delete order by id
     * @param order
     * @throws ExceptionCMS
     */
    void deleteOrder(Order order) throws ExceptionCMS;

}
