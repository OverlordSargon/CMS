package com.zaico.cms.servicies.interfaces;

import com.zaico.cms.entities.Skill;
import com.zaico.cms.utility.ExceptionCMS;

/**
 * Created by nzaitsev on 17.08.2016.
 */
public interface SkillService {

    /**
     *
     * @param id
     * @return
     * @throws ExceptionCMS
     */
    Skill findSkill(Long id) throws ExceptionCMS;

    /**
     *
     * @param skill
     * @return
     * @throws ExceptionCMS
     */
    Skill createSkill(Skill skill) throws ExceptionCMS;

    /**
     * Update skill
     */
    Skill updateSkill(Skill skill) throws ExceptionCMS;

    /**
     * Delete Skill
     * @param skill
     * @throws ExceptionCMS
     */
    void deleteSkill(Skill skill) throws ExceptionCMS;
}
