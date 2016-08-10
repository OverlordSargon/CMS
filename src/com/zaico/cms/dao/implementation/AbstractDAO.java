package com.zaico.cms.dao.implementation;

import com.zaico.cms.dao.interfaces.CommonDAO;
import com.zaico.cms.entities.AbstractEntity;
import com.zaico.cms.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    protected EntityManager em = Persistence.createEntityManagerFactory("cms").createEntityManager();

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
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        return t;
    };

    /**
     * Get object by id
     */
    public T read(Long id) {
        em.getTransaction().begin();
        T resultRead = em.find(type,id);
        em.getTransaction().commit();
        return resultRead;
    }

    /**
     * Updates object
     * @param t Entity object
     */
    public T update(T t) {
        t.setUpdatedAt(new Date());
        em.getTransaction().begin();
        T resultUpdate = em.merge(t);
        em.getTransaction().commit();
        return resultUpdate;
    }

    /**
     * Deletes object
     * @param t Entity, we want to delete
     */
    public void delete(T t) {
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
    }

    /**
     * Get all users
     * @return users like result list
     */
    public List<T> getAll(){
        List<T> resultGetAll = em.createNamedQuery(entityName+".getAll").getResultList();
        return resultGetAll;
    }

    /**
     * Deletes all existing users
     */
    public void deleteAll() {
        em.getTransaction().begin();
        em.createNamedQuery(entityName+".deleteAll").executeUpdate();
        em.getTransaction().commit();
    }
}
