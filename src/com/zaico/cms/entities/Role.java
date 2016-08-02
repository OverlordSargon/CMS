package com.zaico.cms.entities;

import java.util.Date;

/**
 * Created by nzaitsev on 02.08.2016.
 */
public class Role {

    /** VARIABLES */
    /* ID */
    private Long id;

    /* Role */
    private String role;

    /* Created at time*/
    private Date createdAt;

    /*Updated at time*/
    private Date updatedAt;


    /** CONSTRUCTORS */
    /* Default */
    public Role() {

    }

    /* Full */
    public Role(String role) {
        this.role = role;
    }


    /** GETTERS\SETTERS */
    /* Role*/
//  get
    public String getRole() {
        return role;
    }
//  set
    public void setRole(String role) {
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
}
