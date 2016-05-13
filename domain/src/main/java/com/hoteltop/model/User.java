package com.hoteltop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Vlastelin on 03.04.2016.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    private Long userId;

    private String name;

    private String surname;

    private Long bonusPoints = 0L;

    private Long totalPayments = 0L;

    private byte discountPerc = 0;

    private Date modDate;

    private List<Order> orderList;

    public User() {
    }

    public User(Long userId, String name, String surname) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public User(String name, String surname, byte discountPerc, Long totalPayments, Long bonusPoints) {
        this.discountPerc = discountPerc;
        this.totalPayments = totalPayments;
        this.bonusPoints = bonusPoints;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @NotNull
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @NotNull
    @Column(name = "bonusPoints")
    public Long getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(Long bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    @NotNull
    @Column(name = "totalPayments")
    public Long getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(Long totalPayments) {
        this.totalPayments = totalPayments;
    }

    @NotNull
    @Column(name = "discountPerc")
    public byte getDiscountPerc() {
        return discountPerc;
    }

    public void setDiscountPerc(byte discountPerc) {
        this.discountPerc = discountPerc;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
