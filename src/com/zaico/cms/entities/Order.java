package com.zaico.cms.entities;

import java.util.Date;

/**
 * Created by nzaitsev on 02.08.2016.
 */
public class Order {

    /** Variables */
    /* ID */
    private Long id;

    /* Code, for order identification */
    private String code;

    /* Description */
    private String description;

    /* Work interval number */
    private Integer interval;

    /* Day, when needs to be done */
    private Date day;

    /* Client telephone number */
    private int tel_number;

    /* Client name */
    private String clientName;

    /* Created at time*/
    private Date createdAt;

    /*Updated at time*/
    private Date updatedAt;


    /** Constructors */
    /* Default */
    public Order() {

    }

    /* Full */
    public Order(String code, String description, Integer interval,
                 Date day, int tel_number, String clientName) {
        this.code = code;
        this.description = description;
        this.interval = interval;
        this.day = day;
        this.tel_number = tel_number;
        this.clientName = clientName;
    }


    /** GETTERS/SETTERS */
    /* ID */
//  get
    public Long getId() {
        return id;
    }
    //  set
    public void setId(Long id) {
        this.id = id;
    }
    /* Code */
//  get
    public String getCode() {
        return code;
    }
//  set
    public void setCode(String code) {
        this.code = code;
    }
    /* Description */
//  get
    public String getDescription() {
        return description;
    }
//  set
    public void setDescription(String description) {
        this.description = description;
    }
    /* Interval */
//  get
    public Integer getInterval() {
        return interval;
    }
//  set
    public void setInterval(Integer interval) {
        this.interval = interval;
    }
    /* Telephone */
//  get
    public int getTel_number() {
        return tel_number;
    }
//  set
    public void setTel_number(int tel_number) {
        this.tel_number = tel_number;
    }
    /* Client name */
//  get
    public String getclientName() {
        return clientName;
    }
//  set
    public void setclientName(String clientName) {
        this.clientName = clientName;
    }
    /* Created at */
//  get
    public Date getcreatedAt() {
        return createdAt;
    }
//  set
    public void setcreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    /* Updated at */
//  get
    public Date getupdatedAt() {
        return updatedAt;
    }
//  set
    public void setupdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    /** Methods */
    @Override
    public String toString() {
        return this.description.toString() + this.code.toString();
    }
}
