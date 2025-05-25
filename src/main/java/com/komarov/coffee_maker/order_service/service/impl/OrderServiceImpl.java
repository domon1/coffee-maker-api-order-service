package com.komarov.coffee_maker.order_service.service.impl;

import com.komarov.coffee_maker.order_service.model.DTO.OrderWithStatusDTO;
import com.komarov.coffee_maker.order_service.model.Order;
import com.komarov.coffee_maker.order_service.model.OrderItem;
import com.komarov.coffee_maker.order_service.model.OrderItemIngredient;
import com.komarov.coffee_maker.order_service.model.OrderStatus;
import com.komarov.coffee_maker.order_service.repository.OrderRepository;
import com.komarov.coffee_maker.order_service.service.OrderItemService;
import com.komarov.coffee_maker.order_service.service.OrderService;
import com.komarov.coffee_maker.order_service.util.feign.cart.CartServiceFeign;
import com.komarov.coffee_maker.order_service.util.feign.cart.DTO.CartDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    final OrderRepository orderRepository;
    final OrderItemService orderItemService;
    final CartServiceFeign cartServiceFeign;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemService orderItemService, CartServiceFeign cartServiceFeign) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
        this.cartServiceFeign = cartServiceFeign;
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(RuntimeException::new); // TODO change normal exception
    }

    private Order processingOrder(Long userId) {
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.PROCESSING);
        order.setTotalPrice(BigDecimal.ZERO);
        return orderRepository.save(order);
    }

    @Override
    public Order createOrder(Long userId) {
        CartDTO cart = cartServiceFeign.findCartByUserId(userId);

        Order order = processingOrder(userId);

        List<OrderItem> items = orderItemService.createOrderItems(cart.cartItems(), order);
        order.setItems(new HashSet<>(items));
        order.setTotalPrice(calculateTotalPrice(items));

        return orderRepository.save(order);
    }

    @Override
    public OrderStatus updateOrderStatus(Long orderId) {
        Order order = findById(orderId);
        switch (order.getOrderStatus()){
            case PROCESSING -> order.setOrderStatus(OrderStatus.PAYMENT);
            case PAYMENT -> order.setOrderStatus(OrderStatus.COOKING);
            case COOKING -> {
                if (order.getDeliveryAddress() != null) {
                    order.setOrderStatus(OrderStatus.DELIVERY);
                } else {
                    order.setOrderStatus(OrderStatus.COMPLETED);
                }
            }
            case DELIVERY -> order.setOrderStatus(OrderStatus.COMPLETED);
        }

        orderRepository.save(order);
        return order.getOrderStatus();
    }

    @Override
    public List<OrderWithStatusDTO> findCurrentOrder(Long userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);
        return orders.stream()
                .filter(o -> o.getOrderStatus() != OrderStatus.COMPLETED)
                .map(OrderWithStatusDTO::from)
                .toList();
    }

    private BigDecimal calculateTotalPrice(List<OrderItem> items) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (OrderItem item : items) {
            for (OrderItemIngredient ingredient : item.getItemIngredients()) {
                totalPrice = totalPrice.add(
                        ingredient.getPrice().multiply(
                                new BigDecimal(item.getQuantity())));
            }
            totalPrice = totalPrice.add(item.getTotalPrice());
        }

        return totalPrice;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteAllByUserId(id);
    }

    @Override
    public List<Order> findCompletedOrder(Long userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);
        return orders.stream()
                .filter(o -> o.getOrderStatus().equals(OrderStatus.COMPLETED))
                .toList();
    }
}
