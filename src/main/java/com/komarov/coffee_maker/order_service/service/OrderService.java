package com.komarov.coffee_maker.order_service.service;

import com.komarov.coffee_maker.order_service.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<Order> findAllByUserId(Long id);
    Order findById(Long id);
}
