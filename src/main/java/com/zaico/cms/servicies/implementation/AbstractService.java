package com.zaico.cms.servicies.implementation;

import com.zaico.cms.dao.interfaces.CommonDAO;
import com.zaico.cms.servicies.interfaces.CommonService;
import com.zaico.cms.utility.MistakeException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Created by nzaitsev on 17.08.2016.
 * @author ZAITNIK
 * DAO CRUD wrapper
 * Catches exceptions
 */
public class AbstractService<T> implements CommonService<T> {

    /**
     * Logger
     */
    private Log LOG = LogFactory.getLog(CommonService.class);

    /**
     * The entity DAO.
     */
    private CommonDAO<T> DAO;

    /**
     * Creates the entity.
     *
     * @param entity The entity.
     * @return      The created entity.
     * @throws MistakeException
     */
    public T createEntity(T entity) throws MistakeException {
        T createdEntity = DAO.create(entity);
        if (createdEntity == null) {
            StringBuilder message = new StringBuilder("The entity ").append(entity).append(" can't be created.");
            LOG.error(message.toString());
            throw new MistakeException(message.toString());
        }
        return createdEntity;
    }

    /**
     * Loads entity by id.
     * @param id    The id.
     * @return      The found entity.
     * @throws MistakeException
     */
    public T loadEntity(Long id) throws MistakeException {
        T loadedEntity = DAO.read(id);
        if (loadedEntity == null) {
            StringBuilder message = new StringBuilder("The entity with id = ").append(id).append(" is not exist.");
            LOG.error(message.toString());
            throw new MistakeException(message.toString());
        }
        return loadedEntity;
    }

    /**
     * Updates the entity.
     * @param entity The entity.
     * @return      The updated entity.
     * @throws MistakeException
     */
    public T updateEntity(T entity) throws MistakeException {
        T updatedEntity = DAO.update(entity);
        if (updatedEntity == null) {
            StringBuilder message = new StringBuilder("The entity ").append(entity).append(" can't be updated.");
            LOG.error(message.toString());
            throw new MistakeException(message.toString());
        }
        return updatedEntity;
    }

    /**
     * Deletes the entity.
     * @param entity The entity.
     */
    public void deleteEntity(T entity) {
        DAO.delete(entity);
    }

    /**
     * Gets the list of all entries.
     * @return	The list of entries.
     */
    public List<T> getAllEntities() throws MistakeException {

        List<T> entityList = DAO.getAll();
        if (entityList == null) {
            StringBuilder message = new StringBuilder("The entity list is empty.");
            LOG.error(message.toString());
            throw new MistakeException(message.toString());
        }
        return entityList;
    }

    /**
     * Deletes all entities.
     */
    public void deleteAllEntities() {
        DAO.deleteAll();
    }
}
