package com.zaico.cms.entities;

import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 10.08.2016.
 * @author ZAITNIK
 * There are coomon fields for all entities
 * Very hando for set updated_at & created_at in AbstractDAO
 */
public class AbstractEntity {

    /* ID */
    protected Long id;

    /* Created at time*/
    protected Date createdAt;

    /*Updated at time*/
    protected Date updatedAt;

    /* Created at */
    /**
     * Get time, when UserDAO was created
     * @return createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }
    /**
     * Set time, when UserDAO was created
     * @param createdAt
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /* Updated at */
    /**
     * Get time, when UserDAO was created
     * @return updatedAt
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }
    /**
     * Set time, when UserDAO was created
     * @param updatedAt
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
