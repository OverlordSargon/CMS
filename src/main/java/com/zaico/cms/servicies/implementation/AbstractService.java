package com.zaico.cms.servicies.implementation;

/**
 * Created by nzaitsev on 17.08.2016.
 * @author ZAITNIK
 * DAO CRUD wrapper
 * Catches exceptions
 */
public class AbstractService<T>  {

//    /**
//     * Logger
//     */
//    private Log LOG = LogFactory.getLog(CommonService.class);
//
//    /**
//     * The entity DAO.
//     */
//    private CommonDAO<T> DAO;
//
//    /**
//     * Creates the entity.
//     *
//     * @param entity The entity.
//     * @return      The created entity.
//     * @throws ExceptionCMS
//     */
//    public T createEntity(T entity) throws ExceptionCMS {
//        T createdEntity = DAO.create(entity);
//        if (createdEntity == null) {
//            StringBuilder message = new StringBuilder("The entity ").append(entity).append(" can't be created.");
//            LOG.error(message.toString());
//            throw new ExceptionCMS(message.toString());
//        }
//        return createdEntity;
//    }
//
//    /**
//     * Loads entity by id.
//     * @param id    The id.
//     * @return      The found entity.
//     * @throws ExceptionCMS
//     */
//    public T loadEntity(Long id) throws ExceptionCMS {
//        T loadedEntity = DAO.read(id);
//        if (loadedEntity == null) {
//            StringBuilder message = new StringBuilder("The entity with id = ").append(id).append(" is not exist.");
//            LOG.error(message.toString());
//            throw new ExceptionCMS(message.toString());
//        }
//        return loadedEntity;
//    }
//
//    /**
//     * Updates the entity.
//     * @param entity The entity.
//     * @return      The updated entity.
//     * @throws ExceptionCMS
//     */
//    public T updateEntity(T entity) throws ExceptionCMS {
//        T updatedEntity = DAO.update(entity);
//        if (updatedEntity == null) {
//            StringBuilder message = new StringBuilder("The entity ").append(entity).append(" can't be updated.");
//            LOG.error(message.toString());
//            throw new ExceptionCMS(message.toString());
//        }
//        return updatedEntity;
//    }
//
//    /**
//     * Deletes the entity.
//     * @param entity The entity.
//     */
//    public void deleteEntity(T entity) {
//        DAO.delete(entity);
//    }
//
//    /**
//     * Gets the list of all entries.
//     * @return	The list of entries.
//     */
//    public List<T> getAllEntities() throws ExceptionCMS {
//
//        List<T> entityList = DAO.getAll();
//        if (entityList == null) {
//            StringBuilder message = new StringBuilder("The entity list is empty.");
//            LOG.error(message.toString());
//            throw new ExceptionCMS(message.toString());
//        }
//        return entityList;
//    }
//
//    /**
//     * Deletes all entities.
//     */
//    public void deleteAllEntities() {
//        DAO.deleteAll();
//    }
}
