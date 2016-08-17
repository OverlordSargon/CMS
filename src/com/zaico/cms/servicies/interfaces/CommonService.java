package com.zaico.cms.servicies.interfaces;

import com.mysql.jdbc.log.LogFactory;
import com.zaico.cms.utility.MistakeException;
import org.hibernate.annotations.common.util.impl.Log;

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
     * @throws MistakeException
     */
    T createEntity(T entity) throws MistakeException;

    /**
     * Loads T by id.
     *
     * @param id    The id.
     * @return      The found entity.
     * @throws MistakeException
     */
    T loadEntity(Long id) throws MistakeException;

    /**
     * Updates the entity.
     *
     * @param entity The entity.
     * @return      The updated entity.
     * @throws MistakeException
     */
    T updateEntity(T entity) throws MistakeException;

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
    List<T> getAllEntities() throws MistakeException;

    /**
     * Deletes all entities.
     */
    void deleteAllEntities();
}
