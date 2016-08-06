package com.zaico.cms.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nzaitsev on 02.08.2016.
 */
@Entity
@Table(name = "SCHEDULE")
    /* Describes every interval in specified day (WORKPLAN) */
public class Schedule {

    /** Variables */
    /* Id */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SCH_ID")
    private Long id;

    /* Number of work interval */
    @Column(name = "SCH_INTERVAL")
    private Integer interval;

    /* Set type of interval. P - pause, W - working */
    @Column(name = "SCH_FLAG", length = 50)
    private String flag;

    //  /* Relation */
    /* Order, which attached to this worker*/
    @ManyToOne
    private Workplan workplan;

    /* Created at time*/
    @Column( name = "ORD_CREATED_AT")
    private Date createdAt;

    /*Updated at time*/
    @Column( name = "ORD_UPDATED_AT")
    private Date updatedAt;

    /** Constructors*/
    /* Default */
    public Schedule() {

    }

    /* Full */
    /**
     * Full constructor
     * @param flag Interval flag
     * @param interval Interval number
     * @param workplan workplan(day)
     * */
    public Schedule(Integer interval, String flag, Workplan workplan) {
        this.interval = interval;
        this.flag = flag;
        this.workplan = workplan;
    }

    /** GETTERS\SETTERS */
    /* ID */
    /**
     * Get schedule id
     * @return id
     * */
    public Long getId() {
        return id;
    }
    /**
     * Set schedule id
     * @param id
     * */
    public void setId(Long id) {
        this.id = id;
    }

    /* Interval */
    /**
     * Get schedule interval number
     * @return interval
     * */
    public Integer getInterval() {
        return interval;
    }
    /**
     * Set schedule interval number
     * @param interval
     * */
    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    /* Flag */
    /**
     * Get interval flag
     * @return flag
     * */
    public String getFlag() {
        return flag;
    }
    /**
     * Set interval flag
     * @param flag
     * */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /* Workplan */
    /**
     * Get schedule workpaln
     * @return workplan
     * */
    public Workplan getWorkplan() {
        return workplan;
    }
    /**
     * Set schedule workplan
     * @param workplan
     * */
    public void setWorkplan(Workplan workplan) {
        this.workplan = workplan;
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

}
