package com.zaico.cms.servicies.implementation;

import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.interfaces.SkillDAO;
import com.zaico.cms.dao.interfaces.UserDAO;
import com.zaico.cms.entities.Skill;
import com.zaico.cms.entities.User;
import com.zaico.cms.servicies.interfaces.CommonService;
import com.zaico.cms.servicies.interfaces.SkillService;
import com.zaico.cms.utility.ErrorCode;
import com.zaico.cms.utility.ExceptionCMS;


import org.apache.log4j.LogManager; import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 17.08.2016.
 */
public class SkillServiceImpl implements SkillService {

    // Logger
    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);
    // DAO
    private SkillDAO skillDAO = FactoryDAO.getSkillDAOInstance();

    /**
     * Create new skill
     * @param skill
     * @return
     * @throws ExceptionCMS
     */
    public Skill createSkill(Skill skill) throws ExceptionCMS {
        Skill result = null;
        try {
            result = skillDAO.create(skill);
        } catch (Exception e) {
            String errorMessage = "Skill not created:"+new Date();
            throw new ExceptionCMS(errorMessage,ErrorCode.SKILL_CREATE_ERROR);
        }
        return  result;
    }

    /**
     * Get skill by id
     * @param id
     * @return
     * @throws ExceptionCMS
     */
    public Skill findSkill(Long id) throws ExceptionCMS {
        Skill skill = null;
        try {
            skill = skillDAO.read(id);
        } catch (Exception e) {
            String errorMessage = "Skill "+skill.getName()+ " not found: "+new Date();
            LOG.info(errorMessage);
            throw new ExceptionCMS(errorMessage, ErrorCode.SKILL_NOT_FOUND);
        }
        return skill;
    }

    /**
     * Get all skills
     * @return Skill list
     * @throws ExceptionCMS
     */
    public List<Skill> findAllSkills() throws ExceptionCMS {
        try {
            return skillDAO.getAll();
        } catch (Exception e) {
            String errMes = "ALL SKILLS ERROR:"+new Date();
            LOG.info(errMes);
            throw new ExceptionCMS(errMes,ErrorCode.SKILL_NOT_FOUND);
        }
    }

    /**
     * UpdateSkill method implementation
     */
    /**
     * Update skill
     * @param skill
     * @return updated skill
     * @throws ExceptionCMS
     */
    public Skill updateSkill(Skill skill) throws ExceptionCMS {
        Skill upSkill = null;
        try {
            upSkill = skillDAO.update(skill);
        }
        catch (Exception e) {
            String errorMessage = "Skill "+skill.getName()+ " updat error "+new Date();
            LOG.info(errorMessage);
            throw new ExceptionCMS(errorMessage, ErrorCode.SKILL_CREATE_ERROR);
        }
        return upSkill;
    }

    /**
     * Delete skill
     * @param skill
     * @throws ExceptionCMS
     */
    public void deleteSkill(Skill skill) throws ExceptionCMS {
        try {
            skillDAO.delete(skill);
            LOG.info("Skill "+skill.getName()+" deleted at"+new Date());
        } catch (Exception e) {
            String errMess = "Skill "+skill.getName()+" delete error: "+new Date();
            LOG.info(errMess);
            throw new ExceptionCMS(errMess,ErrorCode.SKILL_CANNOT_BE_DELETED);
        }
    }
}
