package com.zaico.cms.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static java.util.logging.Level.ALL;

/**
 * Created by nzaitsev on 02.08.2016.
 */
@Entity
@Table(name = "WORKER")
@NamedQueries(
    {
        @NamedQuery(name = "Worker.getAll", query = "SELECT worker FROM Worker worker ORDER BY W_UPDATED_AT DESC"),
        @NamedQuery(name = "Worker.getBySkill", query = "select  w from Worker w join w.skills s where s.id  = :skill "),
        @NamedQuery(name = "Worker.deleteAll", query = "DELETE FROM Worker")
    })
public class Worker extends AbstractEntity {

    /** Variables */

    /* Name */
    private String name;

    /* Telephone */
    private int telephone;

    /* Relation */
    /* Order, which attached to this worker*/
//    private List<Order> orders;

    /* Workplans for this worker*/
    private  List<Workplan> workplans;

    /* Skills of the worker*/
    private List<Skill> skills;


    /** Constructors */
    /* Default */
    public Worker() {

    }
    /*SemiFull*/

    public Worker(String name, int telephone) {
        this.name = name;
        this.telephone = telephone;
    }

    /* Full */
    /**
     * Full constructor
     * @param name Worker name
     * @param telephone Worker telephone
     * @param skills List of skill
     * @param workplans List of workplans
     */
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
     */
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "W_ID")public Long getId() {
        return id;
    }
    /**
     * Set worker id
     * @param  id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /* Name */
    /**
     * Get worker name
     * @return name
     */
    @Column( name = "W_NAME")
    public String getName() {
        return name;
    }
    /**
     * Set worker name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /* Telephone */
    /**
     * Get worker telephone
     * @return telephone
     */
    @Column( name = "W_TELEPHONE")
    public Integer getTelephone() {
        return telephone;
    }
    /**
     * Set worker telephone
     * @param telephone
     */
    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

//    /* Orders */
//    /**
//     * Get list of orders, which executed by this worker
//     * @return orders
//     */
//    @OneToMany( cascade = CascadeType.PERSIST, mappedBy="worker" )
//    public List<Order> getOrders() {
//        return orders;
//    }
//    /**
//     * Set list of orders, which`ll executed by this worker
//     * @param orders
//     */
//    public void setOrders(List<Order> orders) {
//        this.orders = orders;
//    }

    /* Workplans */
    /**
     * Get worker`s workplans
     * @return workplans
     */
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn( name = "W_ID", referencedColumnName = "W_ID")
//    @OrderBy("date ASC")
    public List<Workplan> getWorkplans() {
        return workplans;
    }
    /**
     * Set worker`s workplans
     * @param workplans
     */
    public void setWorkplans(List<Workplan> workplans) {
        this.workplans = workplans;
    }

    /* Skills */
    /**
     * Get list of worker skill
     * @return skill
     */
    @ManyToMany
    @JoinTable
    (
        name = "WORKER_SKILL",
        joinColumns = @JoinColumn(name = "W_ID", referencedColumnName = "W_ID"),
        inverseJoinColumns = @JoinColumn(name = "S_ID",referencedColumnName = "S_ID")
    )
    public List<Skill> getSkills() {
        return skills;
    }
    /**
     * Set list of worker skill
     * @param skills
     */
    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    /* Created at */
    /**
     * Get time, when role was created
     * @return createdAt
     */
    @Column( name = "W_CREATED_AT")
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
    @Column( name = "W_UPDATED_AT")
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

    /** METHODS */
    @Override
    /**
     * toString
     * @return all info about choosen worker
     */
    public String toString() {
        return name;
    }

    public String infoToString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", telephone=" + telephone +
//                ", orders=" + orders.toString() +
                ", workplans=" + workplans +
//                ", skill=" + skill.toString() +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
