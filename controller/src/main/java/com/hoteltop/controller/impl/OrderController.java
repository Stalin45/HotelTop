package com.hoteltop.controller.impl;

import com.hoteltop.common.OrderService;
import com.hoteltop.common.RoomStatusCalendarService;
import com.hoteltop.common.UserService;
import com.hoteltop.dto.MakeOrderDTO;
import com.hoteltop.model.Order;
import com.hoteltop.model.Room;
import com.hoteltop.model.RoomStatusCalendar;
import com.hoteltop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vlastelin on 02.05.2016.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomStatusCalendarService roomStatusCalendarService;

    @RequestMapping(value = "/show/{page}", method = RequestMethod.GET)
    public ModelAndView showListOrders(@PathVariable int page) {
        Map<String, Object> modelAttrs = new HashMap<>();
        modelAttrs.put("orders", orderService.showListOrders(page));
        modelAttrs.put("page", page);
        modelAttrs.put("entity", "order");
        modelAttrs.put("pageCount", orderService.getPageCount());
        return new ModelAndView("order/showOrders", modelAttrs);
    }

    @RequestMapping(value = "/create/{roomNumber}", method = RequestMethod.GET)
    public String createOrder(Model model,
                              @PathVariable Long roomNumber) {
        model.addAttribute("roomNumber", roomNumber);
        MakeOrderDTO dto = new MakeOrderDTO();
        dto.setRoomNumber(roomNumber);
        model.addAttribute("makeOrderDTO", dto);
        model.addAttribute("users", userService.getAllUsers());
        return "order/createOrder";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String completeCreation(@RequestParam long userId,
                                   @RequestParam long roomNumber,
                                   @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") Date dateFrom,
                                   @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") Date dateTo) {
        long difference = dateTo.getTime() - dateFrom.getTime();
        int days = (int) difference / 1000 / 60 / 60 / 24;

        User user = new User();
        user.setUserId(userId);
        Room room = new Room();
        room.setRoomNumber(roomNumber);
        orderService.makeOrder(user, room, dateFrom, (short) days);
        return "redirect:/order/show/1";
    }

    @RequestMapping(value = "/confirm/{orderId}", method = RequestMethod.GET)
    public String confirmOrder(@PathVariable Long orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        orderService.confirmOrder(order);
        return "redirect:/order/show/1";
    }

    @RequestMapping(value = "/cancel/{orderId}", method = RequestMethod.GET)
    public String cancelOrder(@PathVariable Long orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        orderService.cancelOrder(order);
        return "redirect:/order/show/1";
    }
//
//    @RequestMapping(value = "/edit", method = RequestMethod.POST)
//    public String completeEdition(@RequestParam Long roomNumber, @RequestParam Integer peopleCount,
//                                  @RequestParam Integer price,  @RequestParam String description) {
//        Room room = orderService.getById(roomNumber);
//        room.setPeopleCount(peopleCount.byteValue());
//        room.setPrice(price);
//        room.setDescription(description);
//        orderService.editRoom(room);
//        return "redirect:/room/show/1";
//    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public RoomStatusCalendarService getRoomStatusCalendarService() {
        return roomStatusCalendarService;
    }

    public void setRoomStatusCalendarService(RoomStatusCalendarService roomStatusCalendarService) {
        this.roomStatusCalendarService = roomStatusCalendarService;
    }
}
