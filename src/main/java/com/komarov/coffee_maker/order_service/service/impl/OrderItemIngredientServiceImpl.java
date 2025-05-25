package com.komarov.coffee_maker.order_service.service.impl;

import com.komarov.coffee_maker.order_service.model.OrderItem;
import com.komarov.coffee_maker.order_service.model.OrderItemIngredient;
import com.komarov.coffee_maker.order_service.repository.OrderItemIngredientRepository;
import com.komarov.coffee_maker.order_service.service.OrderItemIngredientService;
import com.komarov.coffee_maker.order_service.util.feign.cart.DTO.CartIngredientDTO;
import com.komarov.coffee_maker.order_service.util.feign.ingredient.DTO.IngredientDTO;
import com.komarov.coffee_maker.order_service.util.feign.ingredient.IngredientServiceFeign;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemIngredientServiceImpl implements OrderItemIngredientService {
    final OrderItemIngredientRepository orderItemIngredientRepository;
    final IngredientServiceFeign ingredientServiceFeign;

    public OrderItemIngredientServiceImpl(OrderItemIngredientRepository orderItemIngredientRepository, IngredientServiceFeign ingredientServiceFeign) {
        this.orderItemIngredientRepository = orderItemIngredientRepository;
        this.ingredientServiceFeign = ingredientServiceFeign;
    }

    @Override
    public List<OrderItemIngredient> createOrderItemIngredients(List<CartIngredientDTO> ingredients, OrderItem orderItem) {
        List<OrderItemIngredient> ingredientList = new ArrayList<>();
        for (CartIngredientDTO ingredient: ingredients) {
            ingredientList.add(createOrderItemIngredient(ingredient.ingredientId(), orderItem));
        }

        return orderItemIngredientRepository.saveAll(ingredientList);
    }

    @Override
    public OrderItemIngredient createOrderItemIngredient(Long ingredientId, OrderItem orderItem) {
        IngredientDTO ingredientDTO = ingredientServiceFeign.findIngredientById(ingredientId);
        OrderItemIngredient ingredient = new OrderItemIngredient();
        ingredient.setIngredientId(ingredientDTO.id());
        ingredient.setOrderItem(orderItem);
        ingredient.setPrice(ingredientDTO.price());

        return ingredient;
    }
}
