package com.hoteltop.model;

import com.hoteltop.util.enums.RoomStatus;
import com.sun.istack.internal.NotNull;

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

    private byte peopleCount = 1;

    private Integer price = 0;

    private String description;

    private Date modDate;

    private List<Order> orderList;

    private List<RoomStatusCalendar> roomStatusCalendarList;

    public Room(byte peopleCount, Integer price) {
        this.peopleCount = peopleCount;
        this.price = price;
    }

    public Room(byte peopleCount, Integer price, String description) {
        this.peopleCount = peopleCount;
        this.price = price;
        this.description = description;
    }

    public Room(byte peopleCount, Integer price, String description, List<RoomStatusCalendar> roomStatusCalendarList) {
        this.peopleCount = peopleCount;
        this.price = price;
        this.description = description;
        this.roomStatusCalendarList = roomStatusCalendarList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomNumber")
    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

    @NotNull
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
