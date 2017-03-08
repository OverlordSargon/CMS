package com.zaico.cms.spring;

import com.zaico.cms.dao.implementation.UserDAOImpl;
import com.zaico.cms.dao.interfaces.UserDAO;
import com.zaico.cms.entities.User;
import com.zaico.cms.utility.ExceptionCMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ZAITNIK on 08.03.2017.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            User user = userDAO.findByName(s);
            Set<GrantedAuthority> roles = new HashSet();
            roles.add(new SimpleGrantedAuthority(user.getRoles().get(0).getRole()));
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getLogin(),user.getPassword(),roles);
            return  userDetails;
        } catch (ExceptionCMS exceptionCMS) {
            exceptionCMS.printStackTrace();
            return null;
        }
    }
}
