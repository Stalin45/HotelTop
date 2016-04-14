package com.hoteltop.common.impl;

import com.hoteltop.common.OrderService;
import com.hoteltop.common.RoomService;
import com.hoteltop.common.UserService;
import com.hoteltop.dao.OrderDAO;
import com.hoteltop.dao.UserDAO;
import com.hoteltop.model.Order;
import com.hoteltop.model.Room;
import com.hoteltop.model.User;
import com.hoteltop.util.enums.OrderStatus;
import com.hoteltop.util.enums.RoomStatus;

import java.util.Date;
import java.util.List;

/**
 * Created by Vlastelin on 08.04.2016.
 */
public class OrderServiceImpl implements OrderService {

    private static final float BONUS_POINTS_MULTIPLIER = 0.1f;

    private static final OrderDAO orderDAO = null;

    private static final UserDAO userDAO = null;

    private static final UserService userService = null;

    private static final RoomService roomService = null;

    public void makeOrder(User user, Room room, Date orderDate, short days) {
        long totalPrice = calculateTotalPrice(days, room.getPrice());
        Long bonusPoints = calculateBonusPoints(totalPrice);

        Order order = new Order(user, room, days, totalPrice, bonusPoints, orderDate, OrderStatus.WAITING);
        orderDAO.create(order);
    }

    private long calculateTotalPrice(short days, int price) {
        long totalPrice = days * price;
        return totalPrice;
    }

    private long calculateBonusPoints(long fullPrice) {
        return (long) Math.ceil(fullPrice * BONUS_POINTS_MULTIPLIER);
    }

    private boolean isRoomAvailable(Room room) {


    }


    public void confirmOrder(Order order) {
        orderDAO.merge(order);

        Room room = order.getRoom();
        //Is room free?
        if (!room.getStatus().equals(RoomStatus.FREE)) {

        }
        room.setStatus(RoomStatus.BOOKED);
        roomService.editRoom(room);

        order.setStatus(OrderStatus.SUCCESSFUL);
        this.editOrder(order);

        User user = order.getUser();
        //Rating service
        userService.increaseBonuses(user);
    }

    public void cancelOrder(Order order) {
        orderDAO.merge(order);

        order.setStatus(OrderStatus.CANCELED);
        this.editOrder(order);

        User user = order.getUser();
        //billingService
//        userService.returnMoney();
    }

    private Long calculateBonusesForOrder(Order order) {
        return 9L;
    }

    public void editOrder(Order order) {

    }

    public void deleteOrder(Order order) {

    }

    public List<Order> showListOrders(int page) {
        return null;
    }

    public Order getOrderInfo(Long id) {
        return null;
    }
}
