package com.zaico.cms.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Zaitnik on 02.08.2016.
 */
@Entity
@Table(name = "CMSORDER")
@NamedQueries(
    {
        @NamedQuery(name = "Cmsorder.getAll", query = "SELECT cmsorder FROM Order cmsorder"),
        @NamedQuery(name = "Cmsorder.deleteAll", query = "DELETE FROM Order")
    })
public class Order extends AbstractEntity {
    /** Variables */

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

    //  set
    /** GETTERS/SETTERS */
    /* ID */
    /**
     * Gets the id
     *  @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "ORD_ID")
    public Long getId() {
        return id;
    }
    /** @param id new id*/
    public void setId(Long id) {
        this.id = id;
    }

    /* ordNumber */
    /**
     * Gets the ordNumber
     *  @return the ordNumber
     */
    @Column( name = "ORD_NUMBER")
    public String getOrdNumber() {
        return ordNumber;
    }
    /** @param ordNumber new ordNumber*/
    public void setOrdNumber(String ordNumber) {
        this.ordNumber = ordNumber;
    }

    /* Description */
    /**
     * Gets the order description
     *  @return the Description
     */
    @Column( name = "ORD_DESCRIPTION")
    public String getDescription() {
        return description;
    }
    /** @param description new Description*/
    public void setDescription(String description) {
        this.description = description;
    }

    /* Date*/
    /**
     * Gets the Day, when must be performed
     *  @return the Date
     */
    @Column( name = "ORD_DATE")
    public Date getDate() {
        return date;
    }
    /** @param date new Date*/
    public void setDate(Date date) {
        this.date = date;
    }

    /* From */
    /**
     * Gets the start time
     *  @return the From
     */
    @Column( name = "ORD_FROM")
    public Date getFrom() {
        return from;
    }
    /** @param from new start time*/
    public void setFrom(Date from) {
        this.from = from;
    }

    /* To */
    /**
     * Gets the end time
     *  @return the id
     */
    @Column( name = "ORD_TO")
    public Date getTo() {
        return to;
    }
    /** @param to new end time*/
    public void setTo(Date to) {
        this.to = to;
    }

    /* Client name */
    /**
     * Gets the this order client name
     *  @return the Client name
     */
    @Column( name = "ORD_CLIENT_NAME")
    public String getClientName() {
        return clientName;
    }
    /** @param clientName new Client name*/
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /* Telephone */
    /**
     * Gets the client telephone number
     *  @return the id
     */
    @Column( name = "ORD_CLIENT_NUM")
    public int getTelNumber() {
        return telNumber;
    }
    /** @param telNumber new client telephone number*/
    public void setTelNumber(int telNumber) {
        this.telNumber = telNumber;
    }

    /* Worker */
    /**
     * Gets the worker, who implement this order
     *  @return the worker
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="W_ID", referencedColumnName = "W_ID")
    public Worker getWorker() {
        return worker;
    }
    /** @param worker new worker*/
    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    /* Created at */
    /**
     * Gets time of creation
     *  @return the created at
     */
    @Column( name = "ORD_CREATED_AT")
    public Date getCreatedAt() {
        return createdAt;
    }
    /** @param createdAt new time of creation*/
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /* Updated at */
    /**
     * Gets the last update time
     *  @return the updated_at
     */
    @Column( name = "ORD_UPDATED_AT")
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
     */
    public String toString() {
        String workerInfo = "";
        if (worker != null) {
            workerInfo = ", worker=" + worker.getName();
        }
        return "Order{" +
                "ordNumber='" + ordNumber + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", from=" + from +
                ", to=" + to +
                ", telNumber=" + telNumber +
                ", clientName='" + clientName + '\'' +
                workerInfo +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
