package com.hoteltop.dao;

import com.hoteltop.model.RoomStatusCalendar;
import com.hoteltop.util.enums.RoomStatus;

import java.util.Date;

/**
 * Created by Vlastelin on 14.04.2016.
 */
public interface RoomStatusCalenarDAO extends GenericDAO<RoomStatusCalendar> {

    public RoomStatusCalendar findByRoomAndDate(Long roomId, Date date);
}
