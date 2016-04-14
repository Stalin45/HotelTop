package com.hoteltop.fortest;

import com.hoteltop.common.OrderService;
import com.hoteltop.common.RoomService;
import com.hoteltop.common.UserService;
import com.hoteltop.common.impl.OrderServiceImpl;
import com.hoteltop.common.impl.RoomServiceImpl;
import com.hoteltop.common.impl.UserServiceImpl;
import com.hoteltop.model.Order;
import com.hoteltop.model.Room;
import com.hoteltop.model.User;

/**
 * Created by Vlastelin on 11.04.2016.
 */
public class Main {

    private static final OrderService orderService = new OrderServiceImpl();

    private static final UserService userService = new UserServiceImpl();

    private static final RoomService roomService = new RoomServiceImpl();

    public static void main(String[] args) {
        User user1 = new User("Ilia", "Kapralov");
        User user2 = new User("Vlad", "Ataev");
        User user3 = new User("Roman", "Timonin");
        User user4 = new User("Ilia", "Ivanov");
        User user5 = new User("Anton", "Vishnyakov");

        Room room1 = new Room((byte) 1, 100, "standart");
        Room room2 = new Room((byte) 2, 200, "standart");
        Room room3 = new Room((byte) 1, 200, "luxe");
        Room room4 = new Room((byte) 3, 400, "luxe");

        userService.createUser(user1);
        userService.createUser(user2);
        userService.createUser(user3);
        userService.createUser(user4);
        userService.createUser(user5);

        roomService.createRoom(room1);
        roomService.createRoom(room2);
        roomService.createRoom(room3);
        roomService.createRoom(room4);

        orderService.makeOrder();
    }
}
