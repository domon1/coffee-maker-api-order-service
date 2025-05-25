package com.komarov.coffee_maker.order_service.util.feign.ingredient.DTO;

import java.math.BigDecimal;

public record IngredientDTO(
        Long id,
        BigDecimal price
) {
}
