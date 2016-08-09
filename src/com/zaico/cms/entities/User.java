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
    private Long id;

    /* Login */
    private String login;

    /* Password */
    private String password;

//  /*Relation*/
    /* Roles of the user */
    private List<Role> roles;

    /* Created at time*/
    private Date createdAt;

    /*Updated at time*/
    private Date updatedAt;


    /** CONSTRUCTORS */
    /* Default */
    public User() {

    }

    /**
     * Full constructor
     * @param login UserInterface name
     * @param password UserInterface password will be ecnrypted
     * */
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /** GETTERS\SETTERS */

    /* ID */
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column( name = "U_ID" )
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
    @Column ( name = "U_LOGIN" )
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
    @Column( name = "U_PASSWORD" )
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
    @ManyToMany
    @JoinTable
        (
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "U_ID", referencedColumnName = "U_ID"),
            inverseJoinColumns = @JoinColumn(name = "R_ID",referencedColumnName = "R_ID")
        )
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
    @Column( name = "U_CREATED_AT" )
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
    @Column( name = "U_UPDATED_AT" )
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
