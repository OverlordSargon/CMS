package com.zaico.cms.servicies.interfaces;

import com.zaico.cms.controllers.worker.WorkerDates;
import com.zaico.cms.entities.Worker;
import com.zaico.cms.entities.Workplan;
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
     * Delete worker by id
     * @param worker
     * @throws ExceptionCMS
     */
    void deleteWorker(Worker worker) throws ExceptionCMS;


    /**
     * Get fist/last day/hour of work
     * @param worker
     * @throws ExceptionCMS
     */
    WorkerDates findWorkTime(Worker worker) throws ExceptionCMS;

    /**
     * Get fist and last workplans
     * @param worker
     * @return List of two workplans
     * @throws ExceptionCMS
     */
    List<Workplan> findEdges(Worker worker) throws ExceptionCMS;


    /**
     * Updates workplan for current worker
     * @param worker
     * @param workerDates
     * @return Worker with updated workplans
     * @throws ExceptionCMS
     */
    Worker updateWorkplans(Worker worker, WorkerDates workerDates) throws ExceptionCMS;

    /**
     * Set wo
     * @param worker
     * @return
     * @throws ExceptionCMS
     */
    Worker setSkillsFromForm(Worker worker) throws ExceptionCMS;
}
