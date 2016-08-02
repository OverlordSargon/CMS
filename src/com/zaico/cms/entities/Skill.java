package com.zaico.cms.entities;

import com.sun.deploy.util.StringUtils;

import java.util.Date;

/**
 * Created by nzaitsev on 02.08.2016.
 */
public class Skill {

    /** Variables */
    /* Id */
    private  Long id;

    /* Name of tne skill */
    private String name;

    /* Description */
    private String description;

    /* Created at time*/
    private Date createdAt;

    /*Updated at time*/
    private Date updatedAt;


    /** Constructors*/
    /* Default */
    public Skill(){

    };

    /* Full */
    public Skill(String name,String description) {
        this.name = name;
        this.description = description;
    }


    /** GETTERS/SETTERS */
    /*Name*/
//  get
    public String getName() {
        return name;
    }
//  set
    public void setName(String name) {
        this.name = name;
    }
    /*Description*/
//  get
    public String getDescription() {
        return description;
    }
//  set
    public void setDescription(String description) {
        this.description = description;
    }
    /* Created at */
//  get
    public Date getcreatedAt() {
        return createdAt;
    }
    //  set
    public void setcreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    /* Updated at */
//  get
    public Date getupdatedAt() {
        return updatedAt;
    }
    //  set
    public void setupdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    /** Methods */
    @Override
    public String toString() {
        String info ="";
        info = "Skill Id: "+this.id+"\t\n";
        info += "Name: "+this.name+"\t\n";
        info += "Description: "+this.description+"\t\n";
        return info;
    }
}
