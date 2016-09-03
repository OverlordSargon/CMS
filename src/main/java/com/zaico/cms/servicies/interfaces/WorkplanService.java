package com.zaico.cms.servicies.interfaces;

import com.zaico.cms.entities.Worker;
import com.zaico.cms.entities.Workplan;
import com.zaico.cms.utility.ExceptionCMS;

import java.util.List;

/**
 * Created by nzaitsev on 17.08.2016.
 */
public interface WorkplanService {
    /**
     * Create new workplan
     * @param workplan
     * @return
     * @throws ExceptionCMS
     */
    Workplan createWorkplan(Workplan workplan) throws ExceptionCMS;

    /**
     * Find workplan by id
     * @param id
     * @return
     * @throws ExceptionCMS
     */
    Workplan findWorkplan(long id) throws ExceptionCMS;

    /**
     * Find all workplan
     * @return List of workplan
     * @throws ExceptionCMS
     */
    List<Workplan> findAllWorkplans() throws ExceptionCMS;

    /**
     * Update workplan entity
     * @param workplan
     * @return updated workplan
     * @throws ExceptionCMS
     */
    Workplan updateWorkplan(Workplan workplan) throws ExceptionCMS;

    /**
     * Delete workplan by id
     * @param workplan
     * @throws ExceptionCMS
     */
    void deleteWorkplan(Workplan workplan) throws ExceptionCMS;

}
