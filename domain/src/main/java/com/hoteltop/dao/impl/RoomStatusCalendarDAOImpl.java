package com.hoteltop.dao.impl;

import com.hoteltop.dao.RoomStatusCalenarDAO;
import com.hoteltop.model.RoomStatusCalendar;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Vlastelin on 13.04.2016.
 */
public class RoomStatusCalendarDAOImpl extends GenericDAOImpl<RoomStatusCalendar> implements RoomStatusCalenarDAO {

    /**
     * Finds status info by room and date
     *
     * @param roomId room id
     * @param date date
     * @return object RoomStatusCalendar if found or null
     */
    public RoomStatusCalendar findByRoomAndDate(Long roomId, Date date) {
        Criteria criteria = getCriteria();
        return (RoomStatusCalendar) criteria.
                createAlias("room", "room").add(Restrictions.eq("room.roomNumber", roomId)).uniqueResult();
    }

    /**
     * Finds status info by room, date and time period
     *
     * @param roomId room id
     * @param date date
     * @param period count of days
     * @return list of objects RoomStatusCalendar if found or null
     */
    public List<RoomStatusCalendar> findByRoomAndDateForPeriod(Long roomId, Date date, short period) {
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(date);
        endDate.add(Calendar.DATE, period);
        Criteria criteria = getCriteria();
        return (List<RoomStatusCalendar>) criteria.
                createAlias("room", "room")
                .add(Restrictions.eq("room.roomNumber", roomId))
                .add(Restrictions.ge("calendarDate", date))
                .add(Restrictions.le("calendarDate", endDate.getTime()))
                .list();
    }

    /**
     * Find all notes for one room
     *
     * @param roomNumber number of room
     * @return list of roomStatusCalendar
     */
    @Override
    public List<RoomStatusCalendar> findByRoomNumber(Long roomNumber) {
        Criteria criteria = getCriteria();
        return (List<RoomStatusCalendar>) criteria.createAlias("room", "room")
                .add(Restrictions.eq("room.roomNumber", roomNumber)).list();
    }
}
