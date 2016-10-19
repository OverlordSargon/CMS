package com.zaico.cms.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 02.08.2016.
 */
@Entity
@Table(name = "ROLE")
@NamedQueries(
    {
        @NamedQuery(name = "Role.getAll", query = "SELECT roles FROM Role roles ORDER BY R_UPDATED_AT ASC "),
        @NamedQuery(name = "Role.deleteAll", query = "DELETE FROM Role")
    })
public class Role extends AbstractEntity {

    /** VARIABLES */

    /* Role */
    private String role;

    /* Description */
    private String description;

    /** CONSTRUCTORS */
    /* Default */
    public Role() {

    }

    public Role(String role) {
        this.role = role;
    }

    /* Full */
    /**
     * Full constructor
     * @param role Name of the role
     */
    public Role(String role, String description) {
        this.role = role;
        this.description = description;
    }


    /** GETTERS\SETTERS */
    /* ID */
    /**
     * Gets the id
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "R_ID")
    public Long getId() {
        return id;
    }
    /**
     * Set id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /* Role*/
    /**
     * Gets the Role
     * @return the role
     */
    @Column(name = "R_ROLE")
    public String getRole() {
        return role;
    }
    /**
     * Set role name
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /* Description */
    /**
     * Gets the description of the role
     * @return role description
     */
    @Column(name = "R_DESCRIPTION")
    public String getDescription() {
        return description;
    }
    /**
     * Set role description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /* Created at */
    /**
     * Get time, when role was created
     * @return createdAt
     */
    @Column(name = "R_CREATED_AT")
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
    @Column(name = "R_UPDATED_AT")
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
     * @return roleInformation
     */
    public String toString() {
        return "Role{" +
                "role='" + role + '\'' +
                ", description='" + description + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public String toString(int a) {
        return role;
    }
}
