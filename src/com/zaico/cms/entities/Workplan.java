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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WP_ID")
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
    @Column(name = "WP_DATE")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "W_ID")
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
    @OneToMany
    @JoinTable
        (
            name="SCHEDULE",
            joinColumns={ @JoinColumn(name="WP_ID", referencedColumnName="WP_ID") },
            inverseJoinColumns={ @JoinColumn(name="SCH_ID", referencedColumnName="SCH_ID", unique=true) }
        )
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
    @Column( name = "WP_CREATED_AT")
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
    @Column( name = "WP_UPDATED_AT")


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
