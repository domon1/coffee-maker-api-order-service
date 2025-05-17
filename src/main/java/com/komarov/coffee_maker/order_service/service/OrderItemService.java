package com.komarov.coffee_maker.order_service.service;


import com.komarov.coffee_maker.order_service.model.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderItemService {
    List<OrderItem> findAllByOrderId(Long id);
}
