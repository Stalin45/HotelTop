package com.hoteltop.common;

import com.hoteltop.model.Order;
import com.hoteltop.model.Room;
import com.hoteltop.model.RoomStatusCalendar;

import java.util.Date;
import java.util.List;

/**
 * Created by Vlastelin on 07.04.2016.
 */
public interface RoomStatusCalendarService {

    public boolean isRoomAvailable(Long roomId, Date date, short period);

    public void bookRoom(Room room, Date date, short period);

    public void takeForApprove(Room room, Date date, short period);

    public void deleteNotesForOrder(Order order);

    public List<RoomStatusCalendar> showListByRoomNumber(Long roomNumber);
}
