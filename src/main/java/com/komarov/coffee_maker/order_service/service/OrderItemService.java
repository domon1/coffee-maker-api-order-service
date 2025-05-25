package com.komarov.coffee_maker.order_service.service;


import com.komarov.coffee_maker.order_service.model.Order;
import com.komarov.coffee_maker.order_service.model.OrderItem;
import com.komarov.coffee_maker.order_service.util.feign.cart.DTO.CartIngredientDTO;
import com.komarov.coffee_maker.order_service.util.feign.cart.DTO.CartItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderItemService {
    OrderItem createOrderItem(Long itemId, List<CartIngredientDTO> ingredientDTOS, Order order);
    List<OrderItem> createOrderItems(List<CartItemDTO> items, Order order);
}
