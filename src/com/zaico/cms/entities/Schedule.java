package com.zaico.cms.entities;

import java.util.Date;

/**
 * Created by nzaitsev on 02.08.2016.
 */
public class Schedule {

    /** Variables */
    /* Id */
    private Long id;

    /* Number of work interval */
    private Integer interval;

    /* Set type of interval. P - pause, W - working, F - free */
    private String flag;

    //  /* Relation */
    /* Order, which attached to this worker*/
    private WorkPlan workPlan;

    /* Created at time*/
    private Date createdAt;

    /*Updated at time*/
    private Date updatedAt;

    /** Constructors*/
    /* Default */
    public Schedule() {

    }

    /* Full */
    public Schedule(Integer interval, String flag, WorkPlan workPlan) {
        this.interval = interval;
        this.flag = flag;
        this.workPlan = workPlan;
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
    /* Interval */
// get
    public Integer getInterval() {
        return interval;
    }
// set
    public void setInterval(Integer interval) {
        this.interval = interval;
    }
    /* Flag */
//get
    public String getFlag() {
        return flag;
    }
//set
    public void setFlag(String flag) {
        this.flag = flag;
    }
    /* Workplan */
//get
    public WorkPlan getWorkPlan() {
        return workPlan;
    }
//set
    public void setWorkPlan(WorkPlan workPlan) {
        this.workPlan = workPlan;
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
