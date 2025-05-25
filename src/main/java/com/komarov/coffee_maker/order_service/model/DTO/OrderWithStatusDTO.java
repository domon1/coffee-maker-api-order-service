package com.komarov.coffee_maker.order_service.model.DTO;

import com.komarov.coffee_maker.order_service.model.Order;

import java.math.BigDecimal;

public record OrderWithStatusDTO(
        Long id,
        BigDecimal totalPrice,
        String status,
        String deliveryAddress
) {
    public static OrderWithStatusDTO from(Order order) {
        return new OrderWithStatusDTO(
                order.getId(),
                order.getTotalPrice(),
                order.getOrderStatus().name(),
                order.getDeliveryAddress()
        );
    }
}
