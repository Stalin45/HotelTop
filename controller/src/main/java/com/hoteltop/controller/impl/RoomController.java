package com.hoteltop.controller.impl;

import com.hoteltop.common.RoomService;
import com.hoteltop.common.impl.RoomServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vlastelin on 02.05.2016.
 */
@Controller
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService = new RoomServiceImpl();

    @RequestMapping("/show")
    public String showListRooms() {
        //roomService.showListRooms(0);
        return "lol";
    }
}
