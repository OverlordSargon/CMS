package com.zaico.cms.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 02.08.2016.
 */
@Entity
@Table(name = "WORKER")
public class Worker {

    /** Variables */
    /* Id */
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column( name = "W_ID")
    private Long id;

    /* Name */
    @Column( name = "W_NAME")
    private String name;

    /* Telephone */
    @Column( name = "W_TELEPHONE")
    private Integer telephone;

//  /* Relation */
    /* Order, which attached to this worker*/
    @OneToMany
    private List<Order> orders;

    /* Workplans for this worker*/
    @OneToMany
    private  List<Workplan> workplans;

    /* Skills of the worker*/
    @OneToMany
    private List<Skill> skills;

    /* Created at time*/
    @Column( name = "ORD_CREATED_AT")
    private Date createdAt;

    /*Updated at time*/
    @Column( name = "ORD_UPDATED_AT")
    private Date updatedAt;


    /** Constructors */
    /* Default */
    public Worker() {

    }

    /* Full */
    /**
     * Full constructor
     * @param name Worker name
     * @param telephone Worker telephone
     * @param skills List of skills
     * @param workplans List of workplans
     * */
    public Worker(String name, Integer telephone, List<Workplan> workplans, List<Skill> skills) {
        this.name = name;
        this.telephone = telephone;
        this.workplans = workplans;
        this.skills = skills;
    }

    /** GETTERS\SETTERS */
    /* ID */
    /**
     * Get worker id
     * @return id
     * */
    public Long getId() {
        return id;
    }
    /**
     * Set worker id
     * @param  id
     * */
    public void setId(Long id) {
        this.id = id;
    }

    /* Name */
    /**
     * Get worker name
     * @return name
     * */
    public String getName() {
        return name;
    }
    /**
     * Set worker name
     * @param name
     * */
    public void setName(String name) {
        this.name = name;
    }

    /* Telephone */
    /**
     * Get worker telephone
     * @return telephone
     * */
    public Integer getTelephone() {
        return telephone;
    }
    /**
     * Set worker telephone
     * @param telephone
     * */
    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    /* Orders */
    /**
     * Get list of orders, which executed by this worker
     * @return orders
     * */
    public List<Order> getOrders() {
        return orders;
    }
    /**
     * Set list of orders, which`ll executed by this worker
     * @param orders
     * */
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    /* Workplans */
    /**
     * Get worker`s workplans
     * @return workplans
     * */
    public List<Workplan> getWorkplans() {
        return workplans;
    }
    /**
     * Set worker`s workplans
     * @param workplans
     * */
    public void setWorkplans(List<Workplan> workplans) {
        this.workplans = workplans;
    }

    /* Skills */
    /**
     * Get list of worker skills
     * @return skills
     * */
    public List<Skill> getSkills() {
        return skills;
    }
    /**
     * Set list of worker skills
     * @param skills
     * */
    public void setSkills(List<Skill> skills) {
        this.skills = skills;
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

    /** METHODS */
    @Override
    /**
     * toString
     * @return all info about choosen worker
     * */
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", telephone=" + telephone +
                ", orders=" + orders +
                ", workplans=" + workplans +
                ", skills=" + skills +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
