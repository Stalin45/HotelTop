package com.hoteltop.common;

import com.hoteltop.model.Order;

import java.util.List;

/**
 * Created by Vlastelin on 08.04.2016.
 */
public interface OrderService {

    public void createOrder(Order order);

    public void editOrder(Order order);

    public void deleteOrder(Order order);

    public List<Order> showListOrders(int page);

    public Order getOrderInfo(Long id);
}
