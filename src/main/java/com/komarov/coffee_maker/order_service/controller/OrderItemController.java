package com.komarov.coffee_maker.order_service.controller;

import com.komarov.coffee_maker.order_service.service.OrderItemService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/order-item")
public class OrderItemController {
    final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }
}
