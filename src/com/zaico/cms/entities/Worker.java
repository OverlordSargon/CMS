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
    private Long id;

    /* Name */
    private String name;

    /* Telephone */
    private Integer telephone;

//  /* Relation */
    /* Order, which attached to this worker*/
    private List<Order> orders;

    /* Workplans for this worker*/
    private  List<Workplan> workplans;

    /* Skills of the worker*/
    private List<Skill> skills;

    /* Created at time*/
    private Date createdAt;

    /*Updated at time*/
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
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column( name = "W_ID")

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
    @Column( name = "W_NAME")

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
    @Column( name = "W_TELEPHONE")

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
    @OneToMany
    @JoinTable
            (
                    name="ORDER",
                    joinColumns={ @JoinColumn(name="O_ID", referencedColumnName="O_ID") },
                    inverseJoinColumns={ @JoinColumn(name="W_ID", referencedColumnName="W_ID", unique=true) }
            )

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
    @OneToMany(mappedBy="worker")
    @JoinTable
            (
                    name="WORKPLAN",
                    joinColumns={ @JoinColumn(name="WP_ID", referencedColumnName="WP_ID") },
                    inverseJoinColumns={ @JoinColumn(name="W_ID", referencedColumnName="W_ID", unique=true) }
            )

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
    @ManyToMany
    @JoinTable
            (
                    name = "WORKER_SKILL",
                    joinColumns = @JoinColumn(name = "W_ID", referencedColumnName = "W_ID"),
                    inverseJoinColumns = @JoinColumn(name = "S_ID",referencedColumnName = "S_ID")
            )

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
    @Column( name = "W_CREATED_AT")

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
    @Column( name = "W_UPDATED_AT")
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
