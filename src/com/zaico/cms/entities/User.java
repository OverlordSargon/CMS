package com.zaico.cms.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 02.08.2016.
 */
@Entity
@Table( name = "USER" )
public class User {

    /** VARIABLES */
    /* ID */
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column( name = "U_ID" )
    private Long id;

    /* Login */
    @Column ( name = "U_LOGIN" )
    private String login;

    /* Password */
    @Column( name = "U_PASSWORD" )
    private String password;

//    /*Relation*/
    /* Role of the user */
    @ManyToMany
    @JoinTable( name = "USER_ROLE",
                joinColumns = @JoinColumn(name = "U_ID", referencedColumnName = "U_ID"),
                inverseJoinColumns = @JoinColumn(name = "S_ID",referencedColumnName = "S_ID"))
    private List<Role> roles;

    /* Created at time*/
    @Column( name = "U_CREATED_AT" )
    private Date createdAt;

    /*Updated at time*/
    @Column( name = "U_UPDATED_AT" )
    private Date updatedAt;


    /** CONSTRUCTORS */
    /* Default */
    public User() {

    }

    /**
     * Full constructor
     * @param login UserInterface name
     * @param password UserInterface password will be ecnrypted
     * @param roles All roles, which will be performed
     * */
    public User(String login, String password, List roles) {
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    /** GETTERS\SETTERS */

    /* ID */
    /**
     * Get user id
     * @return id
     * */
    public Long getId() {
        return id;
    }
    /**
     * Set user id
     * @param id
     * */
    public void setId(Long id) {
        this.id = id;
    }

    /* Login */
    /**
     * Get Login
     * @return login
     * */
    public String getLogin() {
        return login;
    }
    /**
     * Set user login
     * @param login
     * */
    public void setLogin(String login) {
        this.login = login;
    }

    /* Password */
    /**
     * Get password
     * @return password
     * */
    public String getPassword() {
        return password;
    }
    /**
     * Set user password
     * @param password
     * */
    public void setPassword(String password) {
        this.password = password;
    }

    /* Role */
    /**
     * Get all user roles
     * @return roles
     * */
    public List getRole() {
        return roles;
    }
    /**
     * Set user roles
     * @param roles
     * */
    public void setRole(List roles) {
        this.roles = roles;
    }

    /* Created at */
    /**
     * Get time, when UserInterface was created
     * @return createdAt
     * */
    public Date getCreatedAt() {
        return createdAt;
    }
    /**
     * Set time, when UserInterface was created
     * @param createdAt
     * */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /* Updated at */
    /**
     * Get time, when UserInterface was created
     * @return updatedAt
     * */
    public Date getUpdatedAt() {
        return updatedAt;
    }
    /**
     * Set time, when UserInterface was created
     * @param updatedAt
     * */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    /** METHODS */
    @Override
    /**
     * ToString
     * @return login&roles
     * */
    public String toString() {
        return "UserInterface{" +
                "login='" + login + '\'' +
                ", roles=" + roles +
                '}';
    }
}
