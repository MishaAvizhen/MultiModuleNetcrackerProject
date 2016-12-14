package com.netcracker.avizhen.services.service.impl;

import com.netcracker.avizhen.persistence.entity.Advert;
import com.netcracker.avizhen.persistence.entity.Item;
import com.netcracker.avizhen.persistence.entity.Order;
import com.netcracker.avizhen.persistence.entity.User;
import com.netcracker.avizhen.persistence.repository.ItemRepository;
import com.netcracker.avizhen.persistence.repository.OrderRepository;
import com.netcracker.avizhen.persistence.repository.UserRepository;
import com.netcracker.avizhen.services.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * Created by Александр on 13.12.2016.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.getAll();
    }

    @Override
    public Order findOrderById(int orderId) {
        return orderRepository.findOne(orderId);
    }

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void removeOrderById(int orderId) {
        orderRepository.delete(orderId);
    }

    @Override
    public List<Order> findOrdersByUsername(String username) {
        return orderRepository.findOrdersByUsername(username);
    }

    @Override
    public Order addOrderByUserIdAndAdvert(int userId, Advert advert) {
        User user = userRepository.findOne(userId);
        Order order = new Order();
        order.setUser(user);
        java.util.Date now = new java.util.Date();
        order.setRegistrationDate(new Date(now.getTime()));
        order.setStatus("in progress");
        orderRepository.save(order);
        Item item = new Item(order, advert.getCar(), 1);
        itemRepository.save(item);
        return orderRepository.findOne(order.getId());
    }
}
