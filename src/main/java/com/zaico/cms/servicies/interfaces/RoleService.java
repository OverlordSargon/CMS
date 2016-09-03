package com.zaico.cms.servicies.interfaces;

import com.zaico.cms.entities.Role;
import com.zaico.cms.utility.ExceptionCMS;

import java.util.List;

/**
 * Created by nzaitsev on 17.08.2016.
 */
public interface RoleService {

    Role createRole(Role role) throws ExceptionCMS;

    Role findRole(long id) throws ExceptionCMS;

    Role updateRole(Role role) throws ExceptionCMS;

    void deleteRole(Role role) throws ExceptionCMS;

    List<Role> findAllRoles() throws ExceptionCMS;
}
