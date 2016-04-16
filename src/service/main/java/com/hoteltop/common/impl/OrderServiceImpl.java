package com.hoteltop.common.impl;

import com.hoteltop.common.OrderService;
import com.hoteltop.common.RoomService;
import com.hoteltop.common.RoomStatusCalendarService;
import com.hoteltop.common.UserService;
import com.hoteltop.dao.OrderDAO;
import com.hoteltop.dao.UserDAO;
import com.hoteltop.dao.impl.OrderDAOImpl;
import com.hoteltop.dao.impl.UserDAOImpl;
import com.hoteltop.model.Order;
import com.hoteltop.model.Room;
import com.hoteltop.model.RoomStatusCalendar;
import com.hoteltop.model.User;
import com.hoteltop.util.enums.OrderStatus;
import com.hoteltop.util.enums.RoomStatus;
import com.hoteltop.util.exceptions.RoomUnavailableException;

import java.util.Date;
import java.util.List;

/**
 * Created by Vlastelin on 08.04.2016.
 */
public class OrderServiceImpl implements OrderService {

    private static final float BONUS_POINTS_MULTIPLIER = 0.1f;

    private static final OrderDAO orderDAO = new OrderDAOImpl();

    private static final UserDAO userDAO = new UserDAOImpl();

    private static final UserService userService = new UserServiceImpl();

    private static final RoomService roomService = new RoomServiceImpl();

    private static final RoomStatusCalendarService roomStatusCalendarService = new RoomStatusCalendarServiceImpl();

    /**
     * Creates order (need to be confirmed by administrator)
     *
     * @param user client
     * @param room room
     * @param orderDate date of order
     * @param days count of days
     * @throws RoomUnavailableException
     */
    public void makeOrder(User user, Room room, Date orderDate, short days) throws RoomUnavailableException {
        long totalPrice = calculateTotalPrice(days, room.getPrice());
        Long bonusPoints = calculateBonusPoints(totalPrice);

        if (roomStatusCalendarService.isRoomAvailable(room.getRoomNumber(), orderDate)) {
            Order order = new Order(user, room, days, totalPrice, bonusPoints, orderDate, OrderStatus.WAITING);
            orderDAO.create(order);
        } else {
            throw new RoomUnavailableException("Room currently is unavailable, please, choose another one");
        }
    }

    /**
     * Calculates total price of booking
     *
     * @param days count of days
     * @param price price per day
     * @return total price
     */
    private long calculateTotalPrice(short days, int price) {
        long totalPrice = days * price;
        return totalPrice;
    }

    /**
     * Calculates how many bonuses should be gained for order
     *
     * @param fullPrice total price
     * @return count of bonus points
     */
    private long calculateBonusPoints(long fullPrice) {
        return (long) Math.ceil(fullPrice * BONUS_POINTS_MULTIPLIER);
    }

    /**
     * Confirm existing order, book room and increase user bonuses
     *
     * @param order the order
     */
    public void confirmOrder(Order order) {
        orderDAO.merge(order);

        Room room = order.getRoom();
        if (roomStatusCalendarService.isRoomAvailable(room.getRoomNumber(), order.getOrderDate())) {
            roomStatusCalendarService.bookRoom(room, order.getOrderDate());

            order.setStatus(OrderStatus.SUCCESSFUL);
            this.editOrder(order);

            User user = order.getUser();
            userService.increaseBonuses(user);
            userService.changeDiscount();
        } else {
            throw new RoomUnavailableException("Room currently is unavailable, please, choose another one");
        }
    }

    public void cancelOrder(Order order) {
        orderDAO.merge(order);

        order.setStatus(OrderStatus.CANCELED);
        this.editOrder(order);
        //User user = order.getUser();
        //billingService
        //userService.returnMoney();
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
