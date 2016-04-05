package com.hoteltop.model;

import org.hibernate.validator.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Vlastelin on 03.04.2016.
 */
@Entity
@Table(name = "user")
public class User {

    private Long userId;

    private Long bonusPoints = 0L;

    private Long totalPayments = 0L;

    private byte discountPerc = 0;

    private Date modDate;

    private List<Order> orderList;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column
    @NotNull
    public Long getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(Long bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    @Column
    @NotNull
    public Long getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(Long totalPayments) {
        this.totalPayments = totalPayments;
    }

    @Column
    @NotNull
    public byte getDiscountPerc() {
        return discountPerc;
    }

    public void setDiscountPerc(byte discountPerc) {
        this.discountPerc = discountPerc;
    }

    @Version
    @Column
    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
