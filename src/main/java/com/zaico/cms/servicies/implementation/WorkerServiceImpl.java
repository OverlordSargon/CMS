package com.zaico.cms.servicies.implementation;

import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.WorkerDAO;
import com.zaico.cms.entities.User;
import com.zaico.cms.entities.Worker;
import com.zaico.cms.servicies.interfaces.CommonService;
import com.zaico.cms.servicies.interfaces.WorkerService;
import com.zaico.cms.utility.ErrorCode;
import com.zaico.cms.utility.ExceptionCMS;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 17.08.2016.
 */
public class WorkerServiceImpl implements WorkerService {

    private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);
    private WorkerDAO workerDAO = FactoryDAO.getWorkerDAOInstance();

    /**
     * Create new Worker
     * @param worker
     * @return Worker entity
     * @throws ExceptionCMS
     */
    public Worker createWorker(Worker worker) throws ExceptionCMS {
        try {
            return workerDAO.create(worker);
        } catch (Exception e) {
            String errMes = "Worker creation error:"+new Date();
            LOG.info(errMes);
            throw new ExceptionCMS(errMes, ErrorCode.WORKER_CREATION_ERROR);
        }
    }

    /**
     * Find worker be id
     * @param id
     * @return
     * @throws ExceptionCMS
     */
    public Worker findWorker(long id) throws ExceptionCMS {
        try {
            return workerDAO.read(id);
        } catch (Exception e) {
            String errMes = "Worker not found:"+new Date();
            LOG.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.WORKER_NOT_FOUND);
        }
    }

    /**
     * find all workers
     * @return
     * @throws ExceptionCMS
     */
    public List<Worker> findAllWorkers() throws ExceptionCMS {
        try {
            return workerDAO.getAll();
        } catch (Exception e) {
            String errMes = "ALL WORKERS ERROR:"+new Date();
            LOG.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.WORKER_NOT_FOUND);
        }
    }

    /**
     * Get workers by skill id
     * @param skillId
     * @return List of workers
     * @throws ExceptionCMS
     */
    public List<Worker> findWorkersBySkill(long skillId) throws ExceptionCMS {
        try {
            return workerDAO.getBySkill(skillId);
        } catch (Exception e) {
            String errMes = "Find worker by skill error:"+new Date();
            LOG.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.WORKER_NOT_FOUND_BY_SKILL);
        }
    }



    /**
     * Update worker entity
     * @param worker
     * @return
     * @throws ExceptionCMS
     */
    public Worker updateWorker(Worker worker) throws ExceptionCMS {
        try {
            return workerDAO.update(worker);
        } catch (Exception e) {
            String errMes = "Worker update error :"+new Date();
            LOG.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.WORKER_CANNOT_BE_UPDATED);
        }
    }

    /**
     * Set new flags 4 worker
     * @param worker
     * @param timeFrom
     * @param timeTo
     * @return
     * @throws ExceptionCMS
     */
    public Worker updateFlag(Worker worker, String timeFrom, String timeTo) throws ExceptionCMS {
        DateFormat date = new SimpleDateFormat("dd-MM-y");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");


        return worker;
    }

    /**
     * Delete worker entity
     * @param worker
     * @throws ExceptionCMS
     */
    public void deleteWorker(Worker worker) throws ExceptionCMS {
        try {
            workerDAO.delete(worker);
        } catch (Exception e) {
            String errMes = "Worker delete error :"+new Date();
            LOG.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.WORKER_CANNOT_BE_DELETED);
        }
    }
}
