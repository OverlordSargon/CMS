package com.zaico.cms.dao.interfaces;

import com.zaico.cms.entities.Order;
import com.zaico.cms.entities.Worker;

import java.util.List;

/**
 * Created by nzaitsev on 10.08.2016.
 * @author ZAITNIK
 */
public interface OrderDAO extends CommonDAO<Order>{
    /**
     * Methods
     */
    List<Order> getByWorker(Worker worker);
}
