package com.zaico.cms.servicies.interfaces;

import com.zaico.cms.entities.Worker;
import com.zaico.cms.utility.ExceptionCMS;

import java.util.List;

/**
 * Created by nzaitsev on 17.08.2016.
 */
public interface WorkerService {

    /**
     * Create new worker
     * @param worker
     * @return
     * @throws ExceptionCMS
     */
    Worker createWorker(Worker worker) throws ExceptionCMS;

    /**
     * Find worker by id
     * @param id
     * @return
     * @throws ExceptionCMS
     */
    Worker findWorker(long id) throws ExceptionCMS;

    /**
     * Find all worker
     * @return List of worker
     * @throws ExceptionCMS
     */
    List<Worker> findAllWorkers() throws ExceptionCMS;

    /**
     * Update worker entity
     * @param worker
     * @return updated worker
     * @throws ExceptionCMS
     */
    Worker updateWorker(Worker worker) throws ExceptionCMS;

    /**
     * Delete worker by id
     * @param worker
     * @throws ExceptionCMS
     */
    void deleteWorker(Worker worker) throws ExceptionCMS;

}
