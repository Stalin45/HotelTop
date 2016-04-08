package com.hoteltop.common.impl;

import com.hoteltop.common.OrderService;
import com.hoteltop.dao.OrderDAO;
import com.hoteltop.dao.impl.OrderDAOImpl;
import com.hoteltop.model.Order;

import java.util.List;

/**
 * Created by Vlastelin on 08.04.2016.
 */
public class OrderServiceImpl implements OrderService {

    public static final OrderDAO orderDAO = null;

    public void createOrder(Order order) {
        orderDAO.create(order);

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
