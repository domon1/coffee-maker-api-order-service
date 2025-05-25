package com.komarov.coffee_maker.order_service.util.feign.cart;

import com.komarov.coffee_maker.order_service.util.feign.cart.DTO.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "cart-consumer", url = "http://localhost:8081/api/v1/cart")
public interface CartServiceFeign {
    @GetMapping(value = "/{userId}")
    CartDTO findCartByUserId(@PathVariable Long userId);

    @PostMapping(value = "/clear/{userId}")
    ResponseEntity<?> clearCart(@PathVariable Long userId);
}
