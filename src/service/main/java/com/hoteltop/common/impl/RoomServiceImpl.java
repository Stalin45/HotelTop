package com.hoteltop.common.impl;

import com.hoteltop.common.RoomService;
import com.hoteltop.dao.RoomDAO;
import com.hoteltop.dao.impl.RoomDAOImpl;
import com.hoteltop.model.Room;

import java.util.List;

/**
 * Created by Vlastelin on 08.04.2016.
 */
public class RoomServiceImpl implements RoomService {

    RoomDAO roomDAO = new RoomDAOImpl();

    public List<Room> showRooms(int page) {
        //paging??
        return null;
    }

    public List<Room> getById(Long id) {
        List<Room> roomList = roomDAO.findById(id);
    }

    public void createRoom(Room room) {
        roomDAO.create(room);
    }

    public void editRoom(Room room) {
        //???
        roomDAO.merge(room);
        roomDAO.update(room);
    }

    public void deleteRoom(Room room) {
        roomDAO.delete(room);
    }
}
