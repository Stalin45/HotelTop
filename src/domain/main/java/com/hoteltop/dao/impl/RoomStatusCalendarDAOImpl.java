package com.hoteltop.dao.impl;

import com.hoteltop.dao.RoomStatusCalenarDAO;
import com.hoteltop.model.RoomStatusCalendar;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.Date;

/**
 * Created by Vlastelin on 13.04.2016.
 */
public class RoomStatusCalendarDAOImpl extends GenericDAOImpl<RoomStatusCalendar> implements RoomStatusCalenarDAO {

    public RoomStatusCalendar findByRoomAndDate(Long roomId, Date date) {
        Criteria criteria = getCriteria();
        return (RoomStatusCalendar) criteria.
                createAlias("room", "room").add(Restrictions.eq("roomNUmber", roomId)).uniqueResult();
    }
}
