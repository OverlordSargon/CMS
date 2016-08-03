package com.zaico.cms.entities;

import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 02.08.2016.
 */
public class Worker {

    /** Variables */
    /* Id */
    private Long id;

    /* Name */
    private String name;

    /* Telephone */
    private Integer telephone;

//  /* Relation */
    /* Order, which attached to this worker*/
    private List<Order> orders;

    /* Workplans for this worker*/
    private  List<Workplan> workplans;

    /* Skills of the worker*/
    private List<Skill> skills;

    /* Created at time*/
    private Date createdAt;

    /*Updated at time*/
    private Date updatedAt;


    /** Constructors */
    /* Default */
    public Worker() {

    }

    /* Full */
    public Worker(String name, Integer telephone) {
        this.name = name;
        this.telephone = telephone;
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

    /* Name */
//  get
    public String getName() {
        return name;
    }
//  set
    public void setName(String name) {
        this.name = name;
    }

    /* Telephone */
//  get
    public Integer getTelephone() {
        return telephone;
    }
//  set
    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    /* Orders */
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    /* Workplans */
    public List<Workplan> getWorkplans() {
        return workplans;
    }

    public void setWorkplans(List<Workplan> workplans) {
        this.workplans = workplans;
    }

    /* Skills */

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
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

    /** METHODS */


}
