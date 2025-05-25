package com.komarov.coffee_maker.order_service.util.feign.cart.DTO;

import java.util.List;

public record CartItemDTO(
        Long id,
        Long itemId,
        Integer quantity,
        List<CartIngredientDTO> ingredients
) {
}
