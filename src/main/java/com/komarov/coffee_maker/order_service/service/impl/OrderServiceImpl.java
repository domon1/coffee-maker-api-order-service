package com.komarov.coffee_maker.order_service.service.impl;

import com.komarov.coffee_maker.order_service.model.Order;
import com.komarov.coffee_maker.order_service.repository.OrderRepository;
import com.komarov.coffee_maker.order_service.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public List<Order> findAllByUserId(Long id) {
        return List.of();
    }

    @Override
    public Order findById(Long id) {
        return null;
    }
}
