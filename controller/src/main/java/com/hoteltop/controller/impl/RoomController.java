package com.hoteltop.controller.impl;

import com.hoteltop.common.RoomService;
import com.hoteltop.common.impl.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vlastelin on 02.05.2016.
 */
@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/show/{page}", method = RequestMethod.GET)
    public ModelAndView showListRooms(@PathVariable int page) {
        Map<String, Object> modelAttrs = new HashMap<>();
        modelAttrs.put("rooms", roomService.showListRooms(page));
        modelAttrs.put("page", page);
        modelAttrs.put("pageCount", roomService.getPageCount());
        return new ModelAndView("room/rooms", modelAttrs);
    }

    @RequestMapping(value = "/edit/{}", method = RequestMethod.GET)
    public ModelAndView editRoom(@PathVariable long roomNumber) {
        return new ModelAndView("room/edit", "room", roomService.getById(roomNumber));
    }

    public RoomService getRoomService() {
        return roomService;
    }

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
