package com.zaico.cms.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ZAITNIK on 06.08.2016.
 * @author ZAITNIK
 *
 */
public abstract class AbstractDAO<T> {

    /**
     * Type variable for generic DAO
     * */
    public Class<T> type;

    /**
     * The entity manager, 4 handy CRUD.
     */
    @PersistenceContext
    protected EntityManager em;

    /**
     * Basic CRUD actions for all
     * */
    abstract public void create();

    /**
     * Get object by id
     * */
    public T readr(Integer id) {
        return em.find(type,id);
    }

    /**
     * Updates object
     * @param entity Entity object
     * */
    public void update(T entity) {
        em.merge(entity);
    }

    /**
     * Deletes object
     * @param entity Entity, we want to delete
     * */
    public void delete(T entity) {
        em.remove(entity);
    }

}
