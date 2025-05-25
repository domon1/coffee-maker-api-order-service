package com.komarov.coffee_maker.order_service.controller;

import com.komarov.coffee_maker.order_service.model.DTO.OrderWithStatusDTO;
import com.komarov.coffee_maker.order_service.model.Order;
import com.komarov.coffee_maker.order_service.service.OrderService;
import com.komarov.coffee_maker.order_service.util.feign.cart.CartServiceFeign;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/order")
public class OrderController {
    final OrderService orderService;
    final CartServiceFeign cartServiceFeign;

    public OrderController(OrderService orderService, CartServiceFeign cartServiceFeign) {
        this.orderService = orderService;
        this.cartServiceFeign = cartServiceFeign;
    }

    @PostMapping(path = "/{userId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.createOrder(userId));
    }

    @PutMapping(path = "/status/{userId}")
    public ResponseEntity<?> updateStatus(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.updateOrderStatus(userId));
    }

    @GetMapping(path = "/completed/{userId}")
    public ResponseEntity<List<Order>> findCompletedOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.findCompletedOrder(userId));
    }

    @GetMapping(path = "/current/{userId}")
    public ResponseEntity<List<OrderWithStatusDTO>> findCurrentOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.findCurrentOrder(userId));
    }

    @DeleteMapping(path = "/delete/{userId}")
    public ResponseEntity<?> delete(@PathVariable Long userId) {
        orderService.deleteOrder(userId);
        return ResponseEntity.ok().build();
    }
}
