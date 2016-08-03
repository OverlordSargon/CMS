package com.zaico.cms.entities;

import java.util.Date;

/**
 * Created by nzaitsev on 02.08.2016.
 */
public class WorkPlan {

    /** Variables */
    /* ID */
    private Long id;

    /* Day, when work need to be done*/
    private Date day;

//    /* Relation */
    /* Worker, who own this WP */
    private Worker worker;

    /* Created at time*/
    private Date createdAt;

    /*Updated at time*/
    private Date updatedAt;


    /** Constructors */
    /* Default */
    public WorkPlan() {

    }

    /* Full */
    public WorkPlan(Date day, Worker worker) {
        this.day = day;
        this.worker = worker;
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
    /* Day */
//  get
    public Date getDay() {
        return day;
    }
//  set
    public void setDay(Date day) {
        this.day = day;
    }
    /* Worker */
//  get
    public Worker getWorker() {
        return worker;
    }
//  set
    public void setWorker(Worker worker) {
        this.worker = worker;
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


    /** Methods */
    @Override
    public String toString() {
        return this.day.toString()+this.worker.getName();
    }
}
