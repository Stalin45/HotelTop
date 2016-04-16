package com.hoteltop.common;

import com.hoteltop.model.Order;
import com.hoteltop.model.Room;
import com.hoteltop.model.User;
import com.hoteltop.util.exceptions.RoomUnavailableException;

import java.util.Date;
import java.util.List;

/**
 * Created by Vlastelin on 08.04.2016.
 */
public interface OrderService {

    public void makeOrder(User user, Room room, Date orderDate, short days) throws RoomUnavailableException;

    public void confirmOrder(Order order);

    public void cancelOrder(Order order);

    public void editOrder(Order order);

    public void deleteOrder(Order order);

    public List<Order> showListOrders(int page);

    public Order getOrderInfo(Long id);
}
