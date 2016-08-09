package com.zaico.cms.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Zaitnik on 02.08.2016.
 */

@Entity
@Table(name = "ORDER")
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
    private Integer telNumber;

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

    /** Full
       *
       * @param ordNumber   Identification number of  order
       * @param description Desc
       * @param date    Day, when must be executed
       * @param from    Start time
       * @param to      End time
       * @param telNumber   Client telephone
       * @param clientName Client name
       * @param worker  Worker, who`ll execute
       *
    */


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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "ORD_ID")
    /**
     * Gets the id
     *  @return the id
     * */
    public Long getId() {
        return id;
    }
    //  set
    /** @param id new id*/
    public void setId(Long id) {
        this.id = id;
    }

    /* ordNumber */
    @Column( name = "ORD_NUMBER")
    /**
     * Gets the ordNumber
     *  @return the ordNumber
     * */
    public String getOrdNumber() {
        return ordNumber;
    }
    /** @param ordNumber new ordNumber*/
    public void setOrdNumber(String ordNumber) {
        this.ordNumber = ordNumber;
    }

    /* Description */
    @Column( name = "ORD_DESCRIPTION")
    /**
     * Gets the order description
     *  @return the Description
     * */
    public String getDescription() {
        return description;
    }
    /** @param description new Description*/
    public void setDescription(String description) {
        this.description = description;
    }

    /* Date*/
    @Column( name = "ORD_DATE")
    /**
     * Gets the Day, when must be performed
     *  @return the Date
     * */
    public Date getDate() {
        return date;
    }
    /** @param date new Date*/
    public void setDate(Date date) {
        this.date = date;
    }

    /* From */
    @Column( name = "ORD_FROM")
    /**
     * Gets the start time
     *  @return the From
     * */
    public Date getFrom() {
        return from;
    }
    /** @param from new start time*/
    public void setFrom(Date from) {
        this.from = from;
    }

    /* To */
    @Column( name = "ORD_TO")
    /**
     * Gets the end time
     *  @return the id
     * */
    public Date getTo() {
        return to;
    }
    /** @param to new end time*/
    public void setTo(Date to) {
        this.to = to;
    }

    /* Client name */
    @Column( name = "ORD_CLIENT_NAME")
    /**
     * Gets the this order client name
     *  @return the Client name
     * */
    public String getClientName() {
        return clientName;
    }
    /** @param clientName new Client name*/
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /* Telephone */
    @Column( name = "ORD_CLIENT_NUM")
    /**
     * Gets the client telephone number
     *  @return the id
     * */
    public int getTelNumber() {
        return telNumber;
    }
    /** @param telNumber new client telephone number*/
    public void setTelNumber(int telNumber) {
        this.telNumber = telNumber;
    }

    /* Worker */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "W_ID")
    /**
     * Gets the worker, who implement this order
     *  @return the worker
     * */
    public Worker getWorker() {
        return worker;
    }
    /** @param worker new worker*/
    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    /* Created at */
    @Column( name = "ORD_CREATED_AT")
    /**
     * Gets time of creation
     *  @return the created at
     * */
    public Date getCreatedAt() {
        return createdAt;
    }
    /** @param createdAt new time of creation*/
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /* Updated at */
    @Column( name = "ORD_UPDATED_AT")
    /**
     * Gets the last update time
     *  @return the updated_at
     * */
    public Date getUpdatedAt() {
        return updatedAt;
    }
    /** @param updatedAt new update time*/
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    /** Methods */
    @Override
    /**
     * ToString
     * @return OrderInfo
     * Return all info about selected order
     * */
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
