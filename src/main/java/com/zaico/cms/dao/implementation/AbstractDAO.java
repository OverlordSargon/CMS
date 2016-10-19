package com.zaico.cms.dao.implementation;

import com.zaico.cms.dao.interfaces.CommonDAO;
import com.zaico.cms.entities.AbstractEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

/**
 * Created by ZAITNIK on 06.08.2016.
 * @author ZAITNIK
 * Abstract DAO with CRUD and basic operations
 */

public abstract class AbstractDAO<T extends AbstractEntity> implements CommonDAO<T>{

    /**
     * Type variable for generic DAO
      */
    public Class<T> type;

    protected String entityName = "";

    /**
     * The entity manager, 4 handy CRUD.
     */
    @PersistenceContext
    protected EntityManager em ;
//    protected EntityManager em = Persistence.createEntityManagerFactory("cms").createEntityManager();

    /**
     * The constructor.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        this.entityName = type.getSimpleName();
    }

    /**
     * Basic CRUD actions for all
      */
    /**
     * Create new instance
     * @param t Entity type
     */
    public T create(T t) {
        t.setCreatedAt(new Date());
        t.setUpdatedAt(new Date());
        em.persist(t);
        return t;
    }

    /**
     * Get object by id
     */
    public T read(Long id) {
        return  em.find(type, id);
    }

    /**
     * Updates object
     * @param t Entity object
     */
    public T update(T t) {
        t.setUpdatedAt(new Date());
        em.getTransaction().commit();
        return t;
    }

    /**
     * Deletes object
     * @param t Entity, we want to delete
     */
    public void delete(T t) {
        em.remove(em.contains(t) ? t : em.merge(t));
    }

    /**
     * Get all user
     * @return user like result list
     */
    public List<T> getAll() {
        if (entityName.equals("Order")) {
            entityName = "Cmsorder";
        }
        return em.createNamedQuery(entityName+".getAll").getResultList();
    }

    /**
     * Deletes all existing user
     */
    public void deleteAll() {
        em.createNamedQuery(entityName+".deleteAll").executeUpdate();
    }
}
