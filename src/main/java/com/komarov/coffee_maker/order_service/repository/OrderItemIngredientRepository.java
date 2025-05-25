package com.komarov.coffee_maker.order_service.repository;

import com.komarov.coffee_maker.order_service.model.OrderItemIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemIngredientRepository extends JpaRepository<OrderItemIngredient, Long> {
}
