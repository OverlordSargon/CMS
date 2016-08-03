package com.zaico.cms.entities;

import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 02.08.2016.
 */
public class Workplan {

    /** Variables */
    /* ID */
    private Long id;

    /* date, when work need to be done*/
    private Date date;

//    /* Relation */
    /* Worker, who own this workplan */
    private Worker worker;

    /* Schedules, included in this workplan */
    private List<Schedule> schedules;

    /* Created at time*/
    private Date createdAt;

    /*Updated at time*/
    private Date updatedAt;


    /** Constructors */
    /* Default */
    public Workplan() {

    }

    /* Full */
    public Workplan(Date date, Worker worker) {
        this.date = date;
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

    /* date */
//  get
    public Date getDate() {
        return date;
    }
//  set
    public void setDate(Date date) {
        this.date = date;
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

    /* Schedules */

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
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


    /** Methods */

    @Override
    public String toString() {
        return "Workplan{" +
                "id=" + id +
                ", date=" + date +
                ", worker=" + worker +
                ", schedules=" + schedules +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
