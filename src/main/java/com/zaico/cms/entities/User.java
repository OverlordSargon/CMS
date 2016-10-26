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
        @NamedQuery(name = "User.getAll", query = "SELECT user FROM User user ORDER BY U_UPDATED_AT DESC"),
        @NamedQuery(name = "User.deleteAll", query = "DELETE FROM User"),
        @NamedQuery(name = "User.getByRole", query = "SELECT u FROM User u WHERE u.roles = :roles"),
        @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.login= :username"),
        @NamedQuery(name = "User.login", query = "SELECT u FROM User u WHERE u.login= :login AND u.password = :password")
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
    /**
     * Get user id
     * @return id
     */
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "U_ID" ) public Long getId() {
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
    /**
     * Get Login
     * @return login
     */
    @Column ( name = "U_LOGIN" )
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
    /**
     * Get password
     * @return password
     */
    @Column( name = "U_PASSWORD" )
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
    /**
     * Get all user role
     * @return role
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    (
        name = "USER_ROLE",
        joinColumns = @JoinColumn(name = "U_ID", referencedColumnName = "U_ID"),
        inverseJoinColumns = @JoinColumn(name = "R_ID",referencedColumnName = "R_ID")
    )
    public List<Role> getRoles() {
        return roles;
    }
    /**
     * Set user role
     * @param roles
     */
    public void setRoles(List roles) {
        this.roles = roles;
    }

    /* Created at */
    /**
     * Get time, when UserDAO was created
     * @return createdAt
     */
    @Column( name = "U_CREATED_AT" )
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
    /**
     * Get time, when UserDAO was created
     * @return updatedAt
     */
    @Column( name = "U_UPDATED_AT" )
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
     * @return login&role
     */
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
//                ", role=" + role.toString() +
                '}';
    }

    public String rolesprint() {
        String rolesStr ="";
        for ( Role role : roles) {
            rolesStr+="<br>"+role.getRole()+"</br>";
        }
        return  rolesStr;
    }
}
