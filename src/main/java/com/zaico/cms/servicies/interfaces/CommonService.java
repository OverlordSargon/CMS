package com.zaico.cms.servicies.interfaces;

import com.zaico.cms.utility.ExceptionCMS;

import java.util.List;

/**
 * Created by nzaitsev on 17.08.2016.
 */
public interface CommonService<T> {
    /**
     * Creates the entity.
     *
     * @param entity The entity.
     * @return      The created entity.
     * @throws ExceptionCMS
     */
    T createEntity(T entity) throws ExceptionCMS;

    /**
     * Loads T by id.
     *
     * @param id    The id.
     * @return      The found entity.
     * @throws ExceptionCMS
     */
    T loadEntity(Long id) throws ExceptionCMS;

    /**
     * Updates the entity.
     *
     * @param entity The entity.
     * @return      The updated entity.
     * @throws ExceptionCMS
     */
    T updateEntity(T entity) throws ExceptionCMS;

    /**
     * Deletes the T.
     *
     * @param entity The T.
     */
    void deleteEntity(T entity);

    /**
     * Gets the list of all entities.
     *
     * @return	The list of entities.
     */
    List<T> getAllEntities() throws ExceptionCMS;

    /**
     * Deletes all entities.
     */
    void deleteAllEntities();
}
