package com.hoteltop.dao.impl;

import com.hoteltop.dao.OrderDAO;
import com.hoteltop.model.Order;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

/**
 * Created by Vlastelin on 06.04.2016.
 */
@Repository
public class OrderDAOImpl extends GenericDAOImpl<Order> implements OrderDAO {
}
