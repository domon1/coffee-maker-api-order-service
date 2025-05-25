package com.komarov.coffee_maker.order_service.service;

import com.komarov.coffee_maker.order_service.model.OrderItem;
import com.komarov.coffee_maker.order_service.model.OrderItemIngredient;
import com.komarov.coffee_maker.order_service.util.feign.cart.DTO.CartIngredientDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderItemIngredientService {
    List<OrderItemIngredient> createOrderItemIngredients(List<CartIngredientDTO> ingredients, OrderItem orderItem);
    OrderItemIngredient createOrderItemIngredient(Long ingredientId, OrderItem orderItem);
}
