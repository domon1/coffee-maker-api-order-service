package com.komarov.coffee_maker.order_service.repository;

import com.komarov.coffee_maker.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);
    void deleteAllByUserId(Long userId);
}
