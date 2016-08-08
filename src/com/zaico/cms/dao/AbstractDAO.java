package com.zaico.cms.dao;

import com.zaico.cms.entities.User;

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
    public User create(User user) {
        em.persist(user);
        return user;
    };

    /**
     * Get object by id
     * */
    public T read(Long id) {
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
