package com.zaico.cms.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 02.08.2016.
 * @author Zaitnik
 */
@Entity
@Table(name = "WORKPLAN")

    /* Workplan means DAY workplan, which devided into intervals in SCHEDULE */

public class Workplan {

    /** Variables */
    /* ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "WP_ID")
    private Long id;

    /* date, when work need to be done*/
    @Column(name = "WP_DATE")
    private Date date;

//    /* Relation */
    /* Worker, who own this workplan */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "W_ID")
    private Worker worker;

    /* Schedules, included in this workplan */
    @OneToMany
    @JoinTable
        (
            name="SCHEDULE",
            joinColumns={ @JoinColumn(name="SCH_ID", referencedColumnName="SCH_ID") },
            inverseJoinColumns={ @JoinColumn(name="WP_ID", referencedColumnName="WP_ID", unique=true) }
        )
    private List<Schedule> schedules;

    /* Created at time*/
    @Column( name = "WP_CREATED_AT")
    private Date createdAt;

    /*Updated at time*/
    @Column( name = "WP_UPDATED_AT")
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
    /**
     * Get workplan id
     * @return id
     * */
    public Long getId() {
        return id;
    }
    /**
     * Set workplan id
     * @param id
     * */
    public void setId(Long id) {
        this.id = id;
    }

    /* date */
    /**
     * Get workplan date (day)
     * @return date
     * */
    public Date getDate() {
        return date;
    }
    /**
     * Set workplan date (day)
     * @param date
     * */
    public void setDate(Date date) {
        this.date = date;
    }

    /* Worker */
    /**
     * Get workplan worker
     * @return worker
     * */
    public Worker getWorker() {
        return worker;
    }
    /**
     * Set workplan worker
     * @param worker
     * */
    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    /* Schedules */
    /**
     * Get schedules for this workplan
     * @return schedules
     * */
    public List<Schedule> getSchedules() {
        return schedules;
    }
    /**
     * Set schedules for this workplan
     * @param schedules
     * */
    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    /* Created at */
    /**
     * Get time, when role was created
     * @return createdAt
     * */
    public Date getCreatedAt() {
        return createdAt;
    }
    /**
     * Set time, when role was created
     * @param createdAt
     * */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /* Updated at */
    /**
     * Get time, when role was created
     * @return updatedAt
     * */
    public Date getUpdatedAt() {
        return updatedAt;
    }
    /**
     * Set time, when role was created
     * @param updatedAt
     * */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    /** Methods */
    /**
     * toString
     * @return workplan full info
     * */
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
