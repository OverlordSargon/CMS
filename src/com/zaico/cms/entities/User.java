package com.zaico.cms.entities;

import java.util.Date;

/**
 * Created by nzaitsev on 02.08.2016.
 */
public class User {

    /** VARIABLES */
    /* ID */
    private Long id;

    /* Login */
    private String login;

    /* Password */
    private String password;

//    /*Relation*/
    /* Role of the user */
    private Role role;

    /* Created at time*/
    private Date createdAt;

    /*Updated at time*/
    private Date updatedAt;


    /** CONSTRUCTORS */
    /* Default */
    public User() {

    }

    /* Full */
    public User(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    /** GETTERS\SETTERS */
    /* Login */
//  get
    public String getLogin() {
        return login;
    }
//  set
    public void setLogin(String login) {
        this.login = login;
    }
    /* Password */
//  get
    public String getPassword() {
        return password;
    }
//  set
    public void setPassword(String password) {
        this.password = password;
    }
    /* Role */
//  get
    public Role getRole() {
        return role;
    }
//  set
    public void setRole(Role role) {
        this.role = role;
    }
    /* Created at*/
//  get
    public Date getcreatedAt() {
        return createdAt;
    }
    //  set
    public void setcreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    /* Updated at*/
//  get
    public Date getupdatedAt() {
        return updatedAt;
    }
    //  set
    public void setupdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /** METHODS */

}
