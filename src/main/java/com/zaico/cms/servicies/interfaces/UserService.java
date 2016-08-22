package com.zaico.cms.servicies.interfaces;

import com.zaico.cms.entities.User;

/**
 * Created by nzaitsev on 17.08.2016.
 */
public interface UserService {

    /**
     * Get user
     */
    User login(String login, String password) ;
}
