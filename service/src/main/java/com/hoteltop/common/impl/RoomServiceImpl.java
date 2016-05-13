package com.hoteltop.common.impl;

import com.hoteltop.common.RoomService;
import com.hoteltop.dao.RoomDAO;
import com.hoteltop.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Vlastelin on 08.04.2016.
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomDAO roomDAO;

    /**
     * Shows list of rooms for one page
     *
     * @param page number of page
     * @return list of rooms
     */
    @Transactional
    public List<Room> showListRooms(int page) {
        return roomDAO.getList(page);
    }

    @Transactional
    public long getPageCount() {
        return roomDAO.getPageCount();
    }

    /**
     * Gets the room
     *
     * @param id id of room
     * @return room object
     */
    @Transactional
    public Room getById(Long id) {
        return roomDAO.findById(id);
    }

    /**
     * Creates room
     *
     * @param room the room
     */
    @Transactional
    public void createRoom(Room room) {
        roomDAO.create(room);
    }

    /**
     * Edits existed room
     *
     * @param room the room
     */
    @Transactional
    public void editRoom(Room room) {
//        roomDAO.merge(room);
        roomDAO.update(room);
    }

    /**
     * Deletes room
     *
     * @param room the room
     */
    @Transactional
    public void deleteRoom(Room room) {
        roomDAO.merge(room);
        roomDAO.delete(room);
    }

    public RoomDAO getRoomDAO() {
        return roomDAO;
    }

    public void setRoomDAO(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }
}
