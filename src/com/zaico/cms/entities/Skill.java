package com.zaico.cms.entities;

import com.sun.deploy.util.StringUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nzaitsev on 02.08.2016.
 * @author Zaitnik
 */
@Entity
@Table( name = "SKILL")
public class Skill {

    /** Variables */
    /* Id */
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column( name = "S_ID")
    private  Long id;

    /* Name of tne skill */
    @Column ( name = "S_NAME")
    private String name;

    /* Description */
    @Column ( name = "S_DESCRIPTION")
    private String description;

    /* Created at time*/
    @Column ( name = "S_CREATED_AT")
    private Date createdAt;

    /*Updated at time*/
    @Column ( name = "S_UPDATED_AT")
    private Date updatedAt;


    /** Constructors*/
    /* Default */
    public Skill(){

    };

    /* Full */
    /**
     *
     * @param name Name of the skill
     * @param description Description of the skill
     *
     * */
    public Skill(String name,String description) {
        this.name = name;
        this.description = description;
    }


    /** GETTERS/SETTERS */
    /* ID */
//  get
    /**
     * Gets the id
     * @return  The id
     */
    public Long getId() {
        return id;
    }
    //  set
    /**
     * Sets the id
     * @param id the new id
     * */
    public void setId(Long id) {
        this.id = id;
    }

    /*Name*/
//  get
    /**
     * Gets the name of the skill
     * @return Skill name
     * */
    public String getName() {
        return name;
    }
//  set
    /**
     * Sets the name of the skill
     * @param name
     * */
    public void setName(String name) {
        this.name = name;
    }

    /*Description*/
    /**
     * Get skill description
     * @retun Skill description
     * */
    public String getDescription() {
        return description;
    }
    /**
     * Sets skill description
     * @param description
     * */
    public void setDescription(String description) {
        this.description = description;
    }

    /* Created at */
    /**
     * Get time, when skill was created
     * @return createdAt
     * */
    public Date getCreatedAt() {
        return createdAt;
    }
    /**
     * Set time, when skill was created
     * @param createdAt
     * */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /* Updated at */
    /**
     * Get time, when skill was created
     * @return updatedAt
     * */
    public Date getUpdatedAt() {
        return updatedAt;
    }
    /**
     * Set time, when skill was created
     * @param updatedAt
     * */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    /** Methods */
    @Override
    /** ToString()
     *  Allows to get full info in string format
     * */
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
