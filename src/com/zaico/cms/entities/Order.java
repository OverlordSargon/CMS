package com.zaico.cms.entities;

import java.util.Date;

/**
 * Created by nzaitsev on 02.08.2016.
 */
public class Order {

    /** Variables */
    /* ID */
    private Long id;

    /* ordNumber, for order identification */
    private String ordNumber;

    /* Description */
    private String description;

    /* date, when needs to be done */
    private Date date;

    /* start time*/
    private Date from;

    /* end time */
    private Date to;

    /* Client telephone number */
    private int telNumber;

    /* Client name */
    private String clientName;

    //  /* Relation */
    /* Order, which attached to this worker*/
    private Worker worker;

    /* Created at time*/
    private Date createdAt;

    /*Updated at time*/
    private Date updatedAt;


    /** Constructors */
    /* Default */
    public Order() {

    }

    /* Full */

    public Order(String ordNumber, String description, Date date,
                 Date from, Date to, int telNumber,
                 String clientName, Worker worker) {
        this.ordNumber = ordNumber;
        this.description = description;
        this.date = date;
        this.from = from;
        this.to = to;
        this.telNumber = telNumber;
        this.clientName = clientName;
        this.worker = worker;
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

    /* ordNumber */
//  get
    public String getOrdNumber() {
        return ordNumber;
    }
//  set
    public void setOrdNumber(String ordNumber) {
        this.ordNumber = ordNumber;
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

    /* Date*/
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /* From */
    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    /* To */
    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    /* Client name */
//  get
    public String getClientName() {
        return clientName;
    }
//  set
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /* Telephone */
//  get
    public int getTelNumber() {
        return telNumber;
    }
    //  set
    public void setTelNumber(int telNumber) {
        this.telNumber = telNumber;
    }

    /* Worker */
//  get
    public Worker getWorker() {
        return worker;
    }
//  set
    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    /* Created at */
//  get
    public Date getCreatedAt() {
        return createdAt;
    }
//  set
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /* Updated at */
//  get
    public Date getUpdatedAt() {
        return updatedAt;
    }
//  set
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    /** Methods */
    @Override
    public String toString() {
        return "Order{" +
                "ordNumber='" + ordNumber + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", from=" + from +
                ", to=" + to +
                ", telNumber=" + telNumber +
                ", clientName='" + clientName + '\'' +
                ", worker=" + worker +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
