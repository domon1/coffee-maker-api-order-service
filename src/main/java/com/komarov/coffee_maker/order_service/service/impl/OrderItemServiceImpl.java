package com.komarov.coffee_maker.order_service.service.impl;

import com.komarov.coffee_maker.order_service.model.OrderItem;
import com.komarov.coffee_maker.order_service.service.OrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService{
    final OrderItemService orderItemService;

    public OrderItemServiceImpl(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }


    @Override
    public List<OrderItem> findAllByOrderId(Long id) {
        return List.of();
    }
}
