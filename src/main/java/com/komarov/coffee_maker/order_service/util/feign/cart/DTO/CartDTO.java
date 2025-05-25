package com.komarov.coffee_maker.order_service.util.feign.cart.DTO;

import java.util.List;

public record CartDTO(
        Long id,
        Long userId,
        List<CartItemDTO> cartItems
) {
}
