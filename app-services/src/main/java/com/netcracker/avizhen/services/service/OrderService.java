package com.netcracker.avizhen.services.service;

import com.netcracker.avizhen.persistence.entity.Advert;
import com.netcracker.avizhen.persistence.entity.Order;

import java.util.List;

/**
 * Created by Александр on 13.12.2016.
 */
public interface OrderService {
    List<Order> findAllOrders();

    Order findOrderById(int orderId);

    Order addOrder(Order order);

    void removeOrderById(int orderId);

    List<Order> findOrdersByUsername(String username);

    Order addOrderByUserIdAndAdvert(int userId, Advert advert);


}
