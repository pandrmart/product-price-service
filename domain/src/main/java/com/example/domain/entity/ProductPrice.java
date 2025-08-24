package com.example.domain.entity;

import com.example.domain.vo.Price;

import java.time.LocalDateTime;

public record ProductPrice(
        Long id,
        Long productId,
        Long brandId,
        Long priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Long priority,
        Price price) {
}
