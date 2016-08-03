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

    /* Set type of interval. P - pause, W - working */
    private String flag;

    //  /* Relation */
    /* Order, which attached to this worker*/
    private Workplan workplan;

    /* Created at time*/
    private Date createdAt;

    /*Updated at time*/
    private Date updatedAt;

    /** Constructors*/
    /* Default */
    public Schedule() {

    }

    /* Full */
    public Schedule(Integer interval, String flag, Workplan workplan) {
        this.interval = interval;
        this.flag = flag;
        this.workplan = workplan;
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
    public Workplan getWorkplan() {
        return workplan;
    }
//set
    public void setWorkplan(Workplan workplan) {
        this.workplan = workplan;
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
