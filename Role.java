package com.zaico.cms.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 02.08.2016.
 */
@Entity
@Table(name = "ROLE")
public class Role {

    /** VARIABLES */
    /* ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "R_ID")
    private Long id;

    /* Role */
    @Column(name = "R_ROLE")
    private String role;

    /* Description */
    @Column(name = "R_DESCRIPTION")
    private String description;

    /* Users, with this role */
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    /* Created at time*/
    @Column(name = "R_CREATED_AT")
    private Date createdAt;

    /*Updated at time*/
    @Column(name = "R_UPDATED_AT")
    private Date updatedAt;


    /** CONSTRUCTORS */
    /* Default */
    public Role() {

    }

    /* Full */
    /**
     * Full constructor
     * @param role Name of the role
     * */
    public Role(String role, String description) {
        this.role = role;
        this.description = description;
    }


    /** GETTERS\SETTERS */
    /* ID */
    /**
     * Gets the id
     * @return the id
     * */
    public Long getId() {
        return id;
    }
    /**
     * Set id
     * @param id
     * */
    public void setId(Long id) {
        this.id = id;
    }

    /* Role*/
    /**
     * Gets the Role
     * @return the role
     * */
    public String getRole() {
        return role;
    }
    /**
     * Set role name
     * @param role
     * */
    public void setRole(String role) {
        this.role = role;
    }

    /* Description */
    /**
     * Gets the description of the role
     * @return role description
     * */
    public String getDescription() {
        return description;
    }
    /**
     * Set role description
     * @param description
     * */
    public void setDescription(String description) {
        this.description = description;
    }

    /* Users */
    /**
     * Gets the users with this role
     * @return the users
     * */
    public List<User> getUsers() {
        return users;
    }
    /**
     * Set users
     * @param users
     * */
    public void setUsers(List<User> users) {
        this.users = users;
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
     * @return roleInformation
     * */
    public String toString() {
        return "Role{" +
                "role='" + role + '\'' +
                ", description='" + description + '\'' +
                ", users=" + users +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
