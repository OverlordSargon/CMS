package com.zaico.cms.dao.interfaces;

import com.zaico.cms.entities.Worker;

import java.util.List;

/**
 * Created by nzaitsev on 10.08.2016.
 * @author ZAITNIK
 */
public interface WorkerDAO extends CommonDAO<Worker> {
    /**
     * Methods
     */
    List<Worker> getBySkill(long skillId);
}
