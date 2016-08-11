package com.zaico.cms.entities;

import com.zaico.cms.dao.implementation.AbstractEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 02.08.2016.
 * @author Zaitnik
 */
@Entity
@Table(name = "WORKPLAN")
@NamedQueries(
    {
        @NamedQuery(name = "Workplan.getAll", query = "SELECT workplan FROM Workplan workplan "),
        @NamedQuery(name = "Workplan.deleteAll", query = "DELETE FROM Workplan")
    })
    /* Workplan means DAY workplan, which devided into intervals in SCHEDULE */

public class Workplan extends AbstractEntity {

    /** Variables */

    /* Description, marks, commentaries */
    private String description;

    /* date, when work need to be done*/
    private Date date;

//    /* Relation */
    /* Worker, who own this workplan */
    private Worker worker;

    /* Schedules, included in this workplan */

    private List<Schedule> schedules;


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
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WP_ID")
    public Long getId() {
        return id;
    }
    /**
     * Set workplan id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /* Description*/
    /**
     * Get description of workplan
     * @return description
     */
    @Column(name = "WP_DESCRIPTION")
    public String getDescription() {
        return description;
    }
    /**
     * Set description of workplan
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /* date */
    /**
     * Get workplan date (day)
     * @return date
     */
    @Column(name = "WP_DATE")
    public Date getDate() {
        return date;
    }
    /**
     * Set workplan date (day)
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /* Worker */
    /**
     * Get workplan worker
     * @return worker
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "W_ID")
    public Worker getWorker() {
        return worker;
    }
    /**
     * Set workplan worker
     * @param worker
     */
    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    /* Schedules */
    /**
     * Get schedules for this workplan
     * @return schedules
     */
    @OneToMany
    @JoinTable
    (
        name="SCHEDULE",
        joinColumns={ @JoinColumn(name="WP_ID", referencedColumnName="WP_ID") },
        inverseJoinColumns={ @JoinColumn(name="SCH_ID", referencedColumnName="SCH_ID", unique=true) }
    )
    public List<Schedule> getSchedules() {
        return schedules;
    }
    /**
     * Set schedules for this workplan
     * @param schedules
     */
    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    /* Created at */
    /**
     * Get time, when role was created
     * @return createdAt
     */
    @Column( name = "WP_CREATED_AT")
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
    @Column( name = "WP_UPDATED_AT")
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


    /** Methods */
    /**
     * toString
     * @return workplan full info
     */
    @Override
    public String toString() {
        return "Workplan{" +
                "id=" + id +
                ", date=" + date +
                ", worker=" + worker.getName() +
                ", schedules=" + schedules +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
