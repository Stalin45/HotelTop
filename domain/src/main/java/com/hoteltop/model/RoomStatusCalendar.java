package com.hoteltop.model;

import com.hoteltop.util.enums.RoomStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Vlastelin on 13.04.2016.
 */
@Entity
@Table(name = "roomStatusCalendar")
public class RoomStatusCalendar implements Serializable {

    private Long noteId;

    private Room room;

    private Date calendarDate;

    private RoomStatus status = RoomStatus.BOOKED;

    private Date modDate;

    public RoomStatusCalendar() {
    }

    public RoomStatusCalendar(Room room, Date calendarDate) {
        this.room = room;
        this.calendarDate = calendarDate;
    }

    public RoomStatusCalendar(Room room, Date calendarDate, RoomStatus status) {
        this.room = room;
        this.calendarDate = calendarDate;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "noteId")
    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "roomNumber")
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @NotNull
    @Temporal(value = TemporalType.DATE)
    @Column(name = "calendarDate")
    public Date getCalendarDate() {
        return calendarDate;
    }

    public void setCalendarDate(Date calendarDate) {
        this.calendarDate = calendarDate;
    }

    @NotNull
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
}
