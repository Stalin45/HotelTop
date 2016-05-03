package com.hoteltop.common.impl;

import com.hoteltop.common.RoomStatusCalendarService;
import com.hoteltop.dao.RoomStatusCalenarDAO;
import com.hoteltop.dao.impl.RoomStatusCalendarDAOImpl;
import com.hoteltop.model.Order;
import com.hoteltop.model.Room;
import com.hoteltop.model.RoomStatusCalendar;
import com.hoteltop.util.enums.RoomStatus;
import com.hoteltop.util.exceptions.HotelTopRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Vlastelin on 08.04.2016.
 */
@Service
public class RoomStatusCalendarServiceImpl implements RoomStatusCalendarService {

    @Autowired
    private static RoomStatusCalenarDAO roomStatusCalenarDAO;

    /**
     * Checks is room available
     *
     * @param roomId room id
     * @param date order date
     * @param period count of days
     * @return true if available
     */
    @Transactional
    public boolean isRoomAvailable(Long roomId, Date date, short period) {
        List<RoomStatusCalendar> statusList =
                roomStatusCalenarDAO.findByRoomAndDateForPeriod(roomId, date, period);

        return (statusList == null || statusList.size() == 0);
    }

    /**
     * Put a note that room must be booked and need to approve
     *
     * @param room room id
     * @param date order date
     * @param period count of days
     */
    @Transactional
    public void takeForApprove(Room room, Date date, short period) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        for (int i = 0; i < period; i++) {
            roomStatusCalenarDAO.create(new RoomStatusCalendar(room, calendar.getTime(), RoomStatus.WAITING_APPROVE));
            calendar.add(Calendar.DATE, 1);
        }
    }

    /**
     * Put a note, that order approved and book room
     *
     * @param room room id
     * @param date order date
     * @param period count of days
     */
    @Transactional
    public void bookRoom(Room room, Date date, short period) {
        List<RoomStatusCalendar> statusList =
                roomStatusCalenarDAO.findByRoomAndDateForPeriod(room.getRoomNumber(), date, period);
        if (statusList == null || statusList.size() != period) {
            throw new HotelTopRuntimeException("Can not find room notes for thee days");
        }
        RoomStatusCalendar roomStatusCalendar = new RoomStatusCalendar(room, date, RoomStatus.BOOKED);
    }

    /**
     * Delete note about order and free room
     *
     * @param order order which relates to notes
     */
    @Transactional
    public void deleteNotesForOrder(Order order) {
        Room room = order.getRoom();
        List<RoomStatusCalendar> statusList =
                roomStatusCalenarDAO.findByRoomAndDateForPeriod(room.getRoomNumber(), order.getOrderDate(), order.getDays());
        for (RoomStatusCalendar statusNote : statusList) {
            roomStatusCalenarDAO.delete(statusNote);
        }
    }

    /**
     * Finds all notes by roomNumber
     *
     * @param roomNumber number of room
     */
    @Transactional
    public List<RoomStatusCalendar> showListByRoomNumber(Long roomNumber) {
        return roomStatusCalenarDAO.findByRoomNumber(roomNumber);
    }

    public static RoomStatusCalenarDAO getRoomStatusCalenarDAO() {
        return roomStatusCalenarDAO;
    }

    public static void setRoomStatusCalenarDAO(RoomStatusCalenarDAO roomStatusCalenarDAO) {
        RoomStatusCalendarServiceImpl.roomStatusCalenarDAO = roomStatusCalenarDAO;
    }
}
