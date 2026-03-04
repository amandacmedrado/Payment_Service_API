package com.experian.payment.api.domain.Products.dto;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        BigDecimal price
) {}
