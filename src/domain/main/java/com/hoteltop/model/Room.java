package com.hoteltop.model;

import com.hoteltop.util.enums.RoomStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Vlastelin on 03.04.2016.
 */
@Entity
@Table(name = "room")
public class Room implements Serializable {

    private Long roomNumber;

    private byte peopleCount;

    private Integer price;

    private String description;

    private RoomStatus status;

    private Date modDate;

    private List<Order> orderList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomNumber")
    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Column(name = "peopleCount")
    public byte getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(byte peopleCount) {
        this.peopleCount = peopleCount;
    }

    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "room")
    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
