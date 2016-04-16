package com.hoteltop.common.impl;

import com.hoteltop.common.RoomStatusCalendarService;
import com.hoteltop.dao.RoomStatusCalenarDAO;
import com.hoteltop.dao.impl.RoomStatusCalendarDAOImpl;
import com.hoteltop.model.Room;
import com.hoteltop.model.RoomStatusCalendar;
import com.hoteltop.util.enums.RoomStatus;
import com.hoteltop.util.exceptions.HotelTopRuntimeException;

import java.util.Date;

/**
 * Created by Vlastelin on 08.04.2016.
 */
public class RoomStatusCalendarServiceImpl implements RoomStatusCalendarService {

    private static RoomStatusCalenarDAO roomStatusCalenarDAO = new RoomStatusCalendarDAOImpl();

    public boolean isRoomAvailable(Long roomId, Date date) {
        RoomStatusCalendar roomStatusCalenar = roomStatusCalenarDAO.findByRoomAndDate(roomId, date);

        return roomStatusCalenar == null;
    }

    public void bookRoom(Room room, Date date) {
        RoomStatusCalendar roomStatusCalendar = new RoomStatusCalendar(room, date, RoomStatus.BOOKED);
    }

    public void takeForApprove(Room room, Date date) {
        RoomStatusCalendar roomStatusCalendar = roomStatusCalenarDAO.findByRoomAndDate(room.getRoomNumber(), date);
        if (roomStatusCalendar == null) {
            roomStatusCalendar = new RoomStatusCalendar(room, date, RoomStatus.WAITING_APPROVE);
        } else {
            roomStatusCalendar.setStatus(RoomStatus.WAITING_APPROVE);
            roomStatusCalenarDAO.update(roomStatusCalendar);
        }
    }

    public void deleteOrder(Room room, Date date) {
        RoomStatusCalendar roomStatusCalendar = roomStatusCalenarDAO.findByRoomAndDate(room.getRoomNumber(), date);
        if (roomStatusCalendar != null) {
            roomStatusCalenarDAO.delete(roomStatusCalendar);
        } else {
            throw new HotelTopRuntimeException("Can not find order for this room on this date");
        }
    }
}
