package com.hoteltop.common;

import com.hoteltop.model.Room;

import java.util.Date;
import java.util.List;

/**
 * Created by Vlastelin on 07.04.2016.
 */
public interface RoomStatusCalendarService {

    public boolean isRoomAvailable(Long roomId, Date date);

    public void bookRoom(Room room, Date date);

    public void takeForApprove(Room room, Date date);

    public void deleteOrder(Room room, Date date);
}
