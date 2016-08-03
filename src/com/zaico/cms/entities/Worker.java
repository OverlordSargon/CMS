package com.zaico.cms.entities;

import java.util.Date;

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
    private Order order;

    /* Created at time*/
    private Date createdAt;

    /*Updated at time*/
    private Date updatedAt;


    /** Constructors */
    /* Default */
    public Worker() {

    }

    /* Full */
    public Worker(Long id, String name, Integer telephone, Order order) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.order = order;
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
    /* Order */
//  get
    public Order getOrder() {
        return order;
    }
//  set
    public void setOrder(Order order) {
        this.order = order;
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
