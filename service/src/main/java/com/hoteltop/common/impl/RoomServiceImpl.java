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

    private static final RoomDAO roomDAO = new RoomDAOImpl();

    /**
     * Shows list of rooms for one page
     *
     * @param page number of page
     * @return list of rooms
     */
    public List<Room> showListRooms(int page) {
        return roomDAO.getList(page);
    }

    /**
     * Gets the room
     *
     * @param id id of room
     * @return room object
     */
    public Room getById(Long id) {
        return roomDAO.findById(id);
    }

    /**
     * Creates room
     *
     * @param room the room
     */
    public void createRoom(Room room) {
        roomDAO.create(room);
    }

    /**
     * Edits existed room
     *
     * @param room the room
     */
    public void editRoom(Room room) {
        roomDAO.merge(room);
        roomDAO.update(room);
    }

    /**
     * Deletes room
     *
     * @param room the room
     */
    public void deleteRoom(Room room) {
        roomDAO.delete(room);
    }
}
