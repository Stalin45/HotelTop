package com.hoteltop.common;

import com.hoteltop.model.Room;

import java.util.List;

/**
 * Created by Vlastelin on 07.04.2016.
 */
public interface RoomService {

    public List<Room> showListRooms(int page);

    public Room getById(Long id);

    public void createRoom(Room room);

    public void editRoom(Room room);

    public void deleteRoom(Room room);
}
