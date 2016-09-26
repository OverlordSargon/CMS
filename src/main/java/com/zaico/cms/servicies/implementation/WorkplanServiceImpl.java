package com.zaico.cms.servicies.implementation;

import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.WorkerDAO;
import com.zaico.cms.dao.interfaces.WorkplanDAO;
import com.zaico.cms.entities.Worker;
import com.zaico.cms.entities.Workplan;
import com.zaico.cms.servicies.interfaces.CommonService;
import com.zaico.cms.servicies.interfaces.WorkplanService;
import com.zaico.cms.utility.ErrorCode;
import com.zaico.cms.utility.ExceptionCMS;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 17.08.2016.
 */
public class WorkplanServiceImpl implements WorkplanService {

    private static final Logger LOG = Logger.getLogger(WorkplanService.class);
    private WorkplanDAO workplanDAO = FactoryDAO.getWorkplanDAOInstance();

    /**
     * Create new Workplan
     * @param workplan
     * @return Workplan entity
     * @throws ExceptionCMS
     */
    public Workplan createWorkplan(Workplan workplan) throws ExceptionCMS {
        try {
            return workplanDAO.create(workplan);
        } catch (Exception e) {
            String errMes = "Workplan creation error:"+new Date();
            LOG.info(errMes);
            throw new ExceptionCMS(errMes, ErrorCode.WORKPLAN_CREATION_ERROR);
        }
    }

    /**
     * Find workplan be id
     * @param id
     * @return
     * @throws ExceptionCMS
     */
    public Workplan findWorkplan(long id) throws ExceptionCMS {
        try {
            return workplanDAO.read(id);
        } catch (Exception e) {
            String errMes = "Workplan not found:"+new Date();
            LOG.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.WORKPLAN_NOT_FOUND);
        }
    }

    public List<Workplan> findAllWorkplans() throws ExceptionCMS {
        try {
            return workplanDAO.getAll();
        } catch (Exception e) {
            String errMes = "ALL WORKPLANS ERROR:"+new Date();
            LOG.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.WORKPLAN_NOT_FOUND);
        }
    }

    /**
     * Update workplan entity
     * @param workplan
     * @return
     * @throws ExceptionCMS
     */
    public Workplan updateWorkplan(Workplan workplan) throws ExceptionCMS {
        try {
            return workplanDAO.update(workplan);
        } catch (Exception e) {
            String errMes = "Workplan update error :"+new Date();
            LOG.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.WORKPLAN_CANNOT_BE_UPDATED);
        }
    }

    /**
     * Delete workplan entity
     * @param workplan
     * @throws ExceptionCMS
     */
    public void deleteWorkplan(Workplan workplan) throws ExceptionCMS {
        try {
            workplanDAO.delete(workplan);
        } catch (Exception e) {
            String errMes = "Workplan delete error :"+new Date();
            LOG.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.WORKPLAN_CANNOT_BE_DELETED);
        }
    }
}
