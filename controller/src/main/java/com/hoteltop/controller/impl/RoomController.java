package com.hoteltop.controller.impl;

import com.hoteltop.common.RoomService;
import com.hoteltop.common.impl.RoomServiceImpl;
import com.hoteltop.model.Room;
import com.hoteltop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
        modelAttrs.put("entity", "room");
        modelAttrs.put("pageCount", roomService.getPageCount());
        return new ModelAndView("room/showRooms", modelAttrs);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createRoom(Model model) {
        model.addAttribute("room", new Room());
        return "room/createRoom";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String completeCreation(@RequestParam Integer peopleCount,
                                   @RequestParam Integer price,
                                   @RequestParam String description) {
        Room room = new Room(peopleCount.byteValue(), price, description);
        roomService.createRoom(room);
        return "redirect:/room/create";
    }

    @RequestMapping(value = "/edit/{roomNumber}", method = RequestMethod.GET)
    public ModelAndView editRoom(@PathVariable long roomNumber) {
        return new ModelAndView("room/editRoom", "room", roomService.getById(roomNumber));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String completeEdition(@RequestParam Long roomNumber, @RequestParam Integer peopleCount,
                                  @RequestParam Integer price,  @RequestParam String description) {
        Room room = roomService.getById(roomNumber);
        room.setPeopleCount(peopleCount.byteValue());
        room.setPrice(price);
        room.setDescription(description);
        roomService.editRoom(room);
        return "redirect:/room/show/1";
    }

    @RequestMapping(value = "/delete/{roomNumber}", method = RequestMethod.GET)
    public String deleteRoom(@PathVariable long roomNumber) {
        Room room = new Room();
        room.setRoomNumber(roomNumber);
        roomService.deleteRoom(room);
        return "redirect:/room/show/1";
    }

    public RoomService getRoomService() {
        return roomService;
    }

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
