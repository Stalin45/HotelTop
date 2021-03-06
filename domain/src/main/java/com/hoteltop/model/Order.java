package com.hoteltop.model;

import com.hoteltop.util.enums.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Vlastelin on 03.04.2016.
 */
@Entity
@Table(name = "booking")
public class Order implements Serializable {

    private Long orderId;

    private User user;

    private Room room;

    private short days;

    private Long totalPrice;

    private Long bonusPoints;

    private Date orderDate;

    private OrderStatus status;

    private Date modDate;

    public Order() {
    }

    public Order(User user, Room room, short days, Long totalPrice, Long bonusPoints, Date orderDate, OrderStatus status) {
        this.user = user;
        this.room = room;
        this.days = days;
        this.totalPrice = totalPrice;
        this.bonusPoints = bonusPoints;
        this.orderDate = orderDate;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "roomNumber")
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Column(name = "days")
    public short getDays() {
        return days;
    }

    public void setDays(short days) {
        this.days = days;
    }

    @Column(name = "totalPrice")
    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Column(name = "bonusPoints")
    public Long getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(Long bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "orderDate")
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Version
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "modDate")
    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }
}
