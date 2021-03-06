package com.hoteltop.common.impl;

import com.hoteltop.common.OrderService;
import com.hoteltop.common.RoomService;
import com.hoteltop.common.RoomStatusCalendarService;
import com.hoteltop.common.UserService;
import com.hoteltop.dao.OrderDAO;
import com.hoteltop.dao.RoomDAO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Vlastelin on 08.04.2016.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final float BONUS_POINTS_MULTIPLIER = 0.1f;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoomDAO roomDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomStatusCalendarService roomStatusCalendarService;

    /**
     * Creates order (need to be confirmed by administrator)
     *
     * @param user client
     * @param room room
     * @param orderDate date of order
     * @param days count of days
     * @throws RoomUnavailableException
     */
    @Transactional
    public Order makeOrder(User user, Room room, Date orderDate, short days) throws RoomUnavailableException {
        Room persistedRoom = roomDAO.findById(room.getRoomNumber());
        User persistedUser = userDAO.findById(user.getUserId());

        long totalPrice = calculateTotalPrice(days, persistedRoom.getPrice());
        Long bonusPoints = calculateBonusPoints(totalPrice);

        if (roomStatusCalendarService.isRoomAvailable(persistedRoom.getRoomNumber(), orderDate, days)) {
            Order order = new Order(persistedUser, persistedRoom, days, totalPrice, bonusPoints, orderDate, OrderStatus.WAITING);
            roomStatusCalendarService.takeForApprove(persistedRoom, orderDate, days);
            return orderDAO.create(order);
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
    @Transactional
    public void confirmOrder(Order order) {
        Order persistedOrder = orderDAO.findById(order.getOrderId());
        Room persitedRoom = roomDAO.findById(persistedOrder.getRoom().getRoomNumber());

        roomStatusCalendarService.bookRoom(persitedRoom, persistedOrder.getOrderDate(), persistedOrder.getDays());

        persistedOrder.setStatus(OrderStatus.SUCCESSFUL);
        editOrder(persistedOrder);

        User user = persistedOrder.getUser();
        user.setTotalPayments(user.getTotalPayments() + persistedOrder.getTotalPrice());
        userService.increaseBonuses(user, persistedOrder.getBonusPoints());
        userService.changeDiscount(user);
        userService.editUser(user);
    }

    /**
     * Cancels order and delete notes of booking
     *
     * @param order the order
     */
    @Transactional
    public void cancelOrder(Order order) {
        Order persistedOrder = orderDAO.findById(order.getOrderId());
        persistedOrder.setStatus(OrderStatus.CANCELED);
        editOrder(persistedOrder);

        roomStatusCalendarService.deleteNotesForOrder(persistedOrder);
        //billingService
        //userService.returnMoney();
    }

    /**
     * Edits order
     *
     * @param order the order
     */
    @Transactional
    public void editOrder(Order order) {
//        orderDAO.merge(order);
        orderDAO.update(order);
    }

    /**
     * Deletes order
     *
     * @param order the order
     */
    @Transactional
    public void deleteOrder(Order order) {
        orderDAO.delete(order);
    }

    @Transactional
    public long getPageCount() {
        return orderDAO.getPageCount();
    }

    /**
     * Gets list of orders for one page
     *
     * @param page page number
     * @return list of orders
     */
    @Transactional
    public List<Order> showListOrders(int page) {
        return orderDAO.getList(page);
    }

    /**
     * Gets order object with detailed info
     *
     * @param id id of order
     * @return order object
     */
    @Transactional
    public Order getOrderInfo(Long id) {
        return orderDAO.findById(id);
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public RoomService getRoomService() {
        return roomService;
    }

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    public RoomStatusCalendarService getRoomStatusCalendarService() {
        return roomStatusCalendarService;
    }

    public void setRoomStatusCalendarService(RoomStatusCalendarService roomStatusCalendarService) {
        this.roomStatusCalendarService = roomStatusCalendarService;
    }

    public RoomDAO getRoomDAO() {
        return roomDAO;
    }

    public void setRoomDAO(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }
}
