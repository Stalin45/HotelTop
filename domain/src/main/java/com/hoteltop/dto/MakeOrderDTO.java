package com.hoteltop.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Vlastelin on 13.05.2016.
 */
public class MakeOrderDTO implements Serializable {

    private Long roomNumber;

    private Long userId;

    private Date dateFrom;

    private Date dateTo;

    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}
