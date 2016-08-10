package com.zaico.cms.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 02.08.2016.
 */
@Entity
@Table( name = "USER" )
@NamedQueries(
    {
        @NamedQuery(name = "User.getAll", query = "SELECT user FROM User user"),
        @NamedQuery(name = "User.deleteAll", query = "DELETE FROM User")
    })
public class User extends AbstractEntity {

    /** VARIABLES */

    /* Login */
    private String login;

    /* Password */
    private String password;

//  /*Relation*/
    /* Role of the user */
    private List<Role> roles;


    /** CONSTRUCTORS */
    /* Default */
    public User() {

    }

    /**
     * Full constructor
     * @param login UserDAO name
     * @param password UserDAO password will be ecnrypted
     */
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /** GETTERS\SETTERS */

    /* ID */
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "U_ID" )
    /**
     * Get user id
     * @return id
     */
    public Long getId() {
        return id;
    }
    /**
     * Set user id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /* Login */
    @Column ( name = "U_LOGIN" )
    /**
     * Get Login
     * @return login
     */
    public String getLogin() {
        return login;
    }
    /**
     * Set user login
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /* Password */
    @Column( name = "U_PASSWORD" )
    /**
     * Get password
     * @return password
     */
    public String getPassword() {
        return password;
    }
    /**
     * Set user password
     * @param password
     */
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
     */
    public List<Role> getRoles() {
        return roles;
    }
    /**
     * Set user roles
     * @param roles
     */
    public void setRoles(List roles) {
        this.roles = roles;
    }

    /* Created at */
    @Column( name = "U_CREATED_AT" )
    /**
     * Get time, when UserDAO was created
     * @return createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }
    /**
     * Set time, when UserDAO was created
     * @param createdAt
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /* Updated at */
    @Column( name = "U_UPDATED_AT" )
    /**
     * Get time, when UserDAO was created
     * @return updatedAt
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }
    /**
     * Set time, when UserDAO was created
     * @param updatedAt
     */

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    /** METHODS */
    @Override
    /**
     * ToString
     * @return login&roles
     */
    public String toString() {
        return "UserDAO{" +
                "login='" + login + '\'' +
                ", roles=" + roles +
                '}';
    }
}
