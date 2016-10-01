package com.zaico.cms.servicies.interfaces;

import com.zaico.cms.entities.Worker;
import com.zaico.cms.utility.ExceptionCMS;

import javax.servlet.http.HttpServletRequest;
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
     * Find worker by id declaration
     * @param skillId
     * @return list of workers with needed skill
     * @throws ExceptionCMS
     */
    List<Worker> findWorkersBySkill(long skillId) throws ExceptionCMS;


        /**
         * Update worker entity
         * @param worker
         * @return updated worker
         * @throws ExceptionCMS
         */
    Worker updateWorker(Worker worker) throws ExceptionCMS;

    /**
     * Set new flags for worker
     * @param worker
     * @param timeFrom
     * @param timeTo
     * @return
     * @throws ExceptionCMS
     */
    Worker updateFlag(Worker worker,String timeFrom, String timeTo) throws ExceptionCMS;

    /**
     * Delete worker by id
     * @param worker
     * @throws ExceptionCMS
     */
    void deleteWorker(Worker worker) throws ExceptionCMS;


    /**
     * Get fist/last day/hour of work
     * @param worker
     * @param request
     * @throws ExceptionCMS
     */
    void findWorkTime(Worker worker, HttpServletRequest request) throws ExceptionCMS;
}
