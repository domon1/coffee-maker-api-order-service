package com.komarov.coffee_maker.order_service.service.impl;

import com.komarov.coffee_maker.order_service.model.Order;
import com.komarov.coffee_maker.order_service.model.OrderItem;
import com.komarov.coffee_maker.order_service.model.OrderItemIngredient;
import com.komarov.coffee_maker.order_service.repository.OrderItemRepository;
import com.komarov.coffee_maker.order_service.service.OrderItemIngredientService;
import com.komarov.coffee_maker.order_service.service.OrderItemService;
import com.komarov.coffee_maker.order_service.util.feign.cart.DTO.CartIngredientDTO;
import com.komarov.coffee_maker.order_service.util.feign.cart.DTO.CartItemDTO;
import com.komarov.coffee_maker.order_service.util.feign.item.DTO.ItemDTO;
import com.komarov.coffee_maker.order_service.util.feign.item.ItemServiceFeign;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService{
    final OrderItemRepository orderItemRepository;
    final ItemServiceFeign itemServiceFeign;
    final OrderItemIngredientService orderItemIngredientService;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, ItemServiceFeign itemServiceFeign, OrderItemIngredientService orderItemIngredientService) {
        this.orderItemRepository = orderItemRepository;
        this.itemServiceFeign = itemServiceFeign;
        this.orderItemIngredientService = orderItemIngredientService;
    }

    @Override
    public OrderItem createOrderItem(Long itemId, List<CartIngredientDTO> ingredientDTOS, Order order) {
        ItemDTO itemDTO = itemServiceFeign.findItemById(itemId);
        OrderItem item = new OrderItem();
        item.setItemId(itemId);
        item.setTotalPrice(itemDTO.price());
        item.setOrder(order);
        item = orderItemRepository.save(item);

        List<OrderItemIngredient> ingredients = orderItemIngredientService.createOrderItemIngredients(ingredientDTOS, item);
        item.setItemIngredients(new HashSet<>(ingredients));

        return item;
    }

    @Override
    public List<OrderItem> createOrderItems(List<CartItemDTO> items, Order order) {
        List<OrderItem> itemList = new ArrayList<>();
        for (CartItemDTO item: items){
            itemList.add(createOrderItem(item.itemId(), item.ingredients(), order));
        }

        return orderItemRepository.saveAll(itemList);
    }
}
