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
        @NamedQuery(name = "Role.getAll", query = "SELECT roles FROM Role roles"),
        @NamedQuery(name = "Role.deleteAll", query = "DELETE FROM Role")
    })
public class Role extends AbstractEntity{

    /** VARIABLES */

    /* Role */
    private String role;

    /* Description */
    private String description;

    /* Users, with this role */
    private List<User> users;


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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "R_ID")
    /**
     * Gets the id
     * @return the id
     */
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
    @Column(name = "R_ROLE")
    /**
     * Gets the Role
     * @return the role
     */
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
    @Column(name = "R_DESCRIPTION")
    /**
     * Gets the description of the role
     * @return role description
     */
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

    /* Users */
    @ManyToMany
    @JoinTable
        (
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "R_ID", referencedColumnName = "R_ID "),
            inverseJoinColumns = @JoinColumn(name = "U_ID",referencedColumnName = "U_ID")
        )
    /**
     * Gets the users with this role
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }
    /**
     * Set users
     * @param users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /* Created at */
    @Column(name = "R_CREATED_AT")
    /**
     * Get time, when role was created
     * @return createdAt
     */
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
    @Column(name = "R_UPDATED_AT")
    /**
     * Get time, when role was created
     * @return updatedAt
     */
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
                ", users=" + users +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
