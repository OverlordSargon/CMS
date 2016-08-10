package com.zaico.cms.dao.interfaces;

import com.zaico.cms.entities.User;

import java.util.List;

/**
 * Created by nzaitsev on 10.08.2016.
 * Methods are common for all entities
 * @author ZAITNIK
 */
public interface CommonDAO<T> {
    /**
     * Allows to create new entity
     */
    T create(T entity);

    /**
     * Get entity by id
     * @param id Needed entity id
     * @return Entity
     */
    T read(Long id);

    /**
     * Updating entity
     * @param entity  entity
     */
    T update(T entity);

    /**
     * Delete entity
     * @param entity  entity
     */
    void delete(T entity);

    /**
     * Allows to get all entities
     * @return entities
     */
    List<T> getAll();

    /**
     * Allows to delete all entities
     */
    void deleteAll();
}
