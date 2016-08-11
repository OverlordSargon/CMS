package com.zaico.cms.dao.implementation;

import com.zaico.cms.dao.interfaces.CommonDAO;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
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
        try {
            t.setCreatedAt(new Date());
            t.setUpdatedAt(new Date());
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        }
        catch ( Exception exp ) {
            em.getTransaction().rollback();
        }
        return t;
    }

    /**
     * Get object by id
     */
    public T read(Long id) {
        T result = null;
        try {
            em.getTransaction().begin();
            result = em.find(type, id);
            em.getTransaction().commit();
        }
        catch (Exception exp) {
            em.getTransaction().rollback();
        }
        return result;
    }

    /**
     * Updates object
     * @param t Entity object
     */
    public T update(T t) {
        T result = null;
        try {
            t.setUpdatedAt(new Date());
            em.getTransaction().begin();
            result = em.merge(t);
            em.getTransaction().commit();
        }
        catch (Exception exp) {
            em.getTransaction().rollback();
        }
        return result;
    }

    /**
     * Deletes object
     * @param t Entity, we want to delete
     */
    public void delete(T t) {
        try {
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
        }
            catch (Exception exp) {
            em.getTransaction().rollback();
        }
    }

    /**
     * Get all users
     * @return users like result list
     */
    public List<T> getAll() {
        List<T> result = null;
        try {
            em.getTransaction().begin();
            result = em.createNamedQuery(entityName+".getAll").getResultList();
            em.getTransaction().commit();
        }
        catch (Exception exp) {
            em.getTransaction().rollback();
        }
        return result;
    }

    /**
     * Deletes all existing users
     */
    public void deleteAll() {
        try {
            em.getTransaction().begin();
            em.createNamedQuery(entityName+".deleteAll").executeUpdate();
            em.getTransaction().commit();
        }
        catch (Exception exp) {
            em.getTransaction().rollback();
        }
    }
}
