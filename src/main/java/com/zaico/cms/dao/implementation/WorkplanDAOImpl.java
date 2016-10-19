package com.zaico.cms.dao.implementation;

import com.zaico.cms.dao.interfaces.WorkplanDAO;
import com.zaico.cms.entities.Workplan;
import org.springframework.stereotype.Repository;

/**
 * Created by nzaitsev on 10.08.2016.
 * @author ZAITNIK
 * Class for implementation abstract & interfaces
 */
@Repository("workplanDao")
public class WorkplanDAOImpl extends AbstractDAO<Workplan> implements WorkplanDAO {

}
