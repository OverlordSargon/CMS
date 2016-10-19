package com.zaico.cms.dao.implementation;

import com.zaico.cms.dao.interfaces.OrderDAO;
import com.zaico.cms.entities.Order;
import com.zaico.cms.entities.Worker;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by nzaitsev on 10.08.2016.
 * @author ZAITNIK
 * Class for implementation abstract & interfaces
 */
@Repository("orderDao")
public class OrderDAOImpl extends AbstractDAO<Order> implements OrderDAO {
    /**
     * Overrited methods for getAll & deleteAll by named queries
     */
    /**
     * Get orders of worker
     * @param worker
     * @return list of orders
     */
    public List<Order> getByWorker(Worker worker) {
        List<Order> result = null;
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Cmsorder.getByWorker");
            query.setParameter("worker",worker);
            result = query.getResultList();
            em.getTransaction().commit();
        }
        catch (Exception exp) {
            em.getTransaction().rollback();
        }
        return result;
    }
}
