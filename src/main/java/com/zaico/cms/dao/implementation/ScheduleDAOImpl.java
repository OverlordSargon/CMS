package com.zaico.cms.dao.implementation;

import com.zaico.cms.dao.interfaces.ScheduleDAO;
import com.zaico.cms.entities.Schedule;
import org.springframework.stereotype.Repository;

/**
 * Created by nzaitsev on 10.08.2016.
 * @author ZAITNIK
 * Class for implementation abstract & interfaces
 */
@Repository("scheduleDao")
public class ScheduleDAOImpl extends AbstractDAO<Schedule> implements ScheduleDAO {

}
