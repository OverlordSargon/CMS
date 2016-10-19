package com.zaico.cms.dao.implementation;

import com.zaico.cms.dao.interfaces.SkillDAO;
import com.zaico.cms.entities.Skill;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nzaitsev on 10.08.2016.
 * @author ZAITNIK
 * Class for implementation abstract & interfaces
 */
@Component
public class SkillDAOImpl extends AbstractDAO<Skill> implements SkillDAO {

}
