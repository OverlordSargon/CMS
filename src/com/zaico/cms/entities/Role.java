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
    /* ID */
//  get
    public Long getId() {
        return id;
    }
    //  set
    public void setId(Long id) {
        this.id = id;
    }
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
    public Date getCreatedAt() {
        return createdAt;
    }
    //  set
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    /* Updated at*/
//  get
    public Date getUpdatedAt() {
        return updatedAt;
    }
    //  set
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
