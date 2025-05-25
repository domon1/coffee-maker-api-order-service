package com.komarov.coffee_maker.order_service.service;

import com.komarov.coffee_maker.order_service.model.DTO.OrderWithStatusDTO;
import com.komarov.coffee_maker.order_service.model.Order;
import com.komarov.coffee_maker.order_service.model.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Order findById(Long id);
    Order createOrder(Long userId);
    void deleteOrder(Long id);
    List<Order> findCompletedOrder(Long userId);
    OrderStatus updateOrderStatus(Long orderId);
    List<OrderWithStatusDTO> findCurrentOrder(Long userId);
}
