package com.zaico.cms.dao.implementation;

import com.zaico.cms.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

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
    private EntityManager em = Persistence.createEntityManagerFactory("cms").createEntityManager();

    /**
     * The constructor.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Basic CRUD actions for all
     * */
    public T create(T t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        return t;
    };

    /**
     * Get object by id
     * */
    public T read(Long id) {
        return em.find(type,id);
    }

    /**
     * Updates object
     * @param t Entity object
     * */
    public void update(T t) {
        em.merge(t);
    }

    /**
     * Deletes object
     * @param t Entity, we want to delete
     * */
    public void delete(T t) {
        em.remove(t);
    }

}
