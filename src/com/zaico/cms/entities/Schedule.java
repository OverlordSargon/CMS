package com.zaico.cms.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nzaitsev on 02.08.2016.
 */
@Entity
@Table(name = "SCHEDULE")
@NamedQueries(
    {
        @NamedQuery(name = "Schedule.getAll", query = "SELECT schedule FROM Schedule schedule"),
        @NamedQuery(name = "Schedule.deleteAll", query = "DELETE FROM Schedule")
    })
    /* Describes every interval in specified day (WORKPLAN) */
public class Schedule extends AbstractEntity {

    /** Variables */

    /* Number of work interval */
    private Integer interval;

    /* Set type of interval. P - pereriv obedenniy, W - working, F - free*/
    private String flag;

    //  /* Relation */
    /* Order, which attached to this worker*/
    private Workplan workplan;


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
     */
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
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCH_ID")public Long getId() {
        return id;
    }
    /**
     * Set schedule id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /* Interval */
    /**
     * Get schedule interval number
     * @return interval
     */
    @Column(name = "SCH_INTERVAL")
    public Integer getInterval() {
        return interval;
    }
    /**
     * Set schedule interval number
     * @param interval
     */
    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    /* Flag */
    /**
     * Get interval flag
     * @return flag
     */
    @Column(name = "SCH_FLAG", length = 50)
    public String getFlag() {
        return flag;
    }
    /**
     * Set interval flag
     * @param flag
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /* Workplan */
    /**
     * Get schedule workpaln
     * @return workplan
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WP_ID")
    public Workplan getWorkplan() {
        return workplan;
    }
    /**
     * Set schedule workplan
     * @param workplan
     */
    public void setWorkplan(Workplan workplan) {
        this.workplan = workplan;
    }

    /* Created at */
    /**
     * Get time, when role was created
     * @return createdAt
     */
    @Column( name = "SCH_CREATED_AT")
    public Date getCreatedAt() {
        return createdAt;
    }
    /**
     * Set time, when role was created
     * @param createdAt
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /* Updated at */
    /**
     * Get time, when role was created
     * @return updatedAt
     */
    @Column( name = "SCH_UPDATED_AT")
    public Date getUpdatedAt() {
        return updatedAt;
    }
    /**
     * Set time, when role was created
     * @param updatedAt
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Methods
     */

    @Override
    public String toString() {
        return "Schedule{" +
                "interval=" + interval +
                ", flag='" + flag + '\'' +
//                ", workplan=" + workplan.getDescription() +
                '}';
    }
}
