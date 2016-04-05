package com.hoteltop.model;

import com.hoteltop.util.enums.RoomStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Vlastelin on 03.04.2016.
 */
@Entity
public class Room {

    private Long roomNumber;

    private byte peopleCount;

    private Integer price;

    private String description;

    private RoomStatus status;

    private Date modDate;

    private List<Order> orderList;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Column
    public byte getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(byte peopleCount) {
        this.peopleCount = peopleCount;
    }

    @Column
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column
    @Enumerated(value = EnumType.STRING)
    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
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
