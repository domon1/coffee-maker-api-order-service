package com.komarov.coffee_maker.order_service.util.feign.item.DTO;

import java.math.BigDecimal;

public record ItemDTO(
        Long id,
        BigDecimal price
) {
}
