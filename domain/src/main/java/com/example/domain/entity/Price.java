package com.example.domain.entity;

import com.example.domain.vo.Money;

import java.time.LocalDateTime;

public record Price(
        Long id,
        Long productId,
        Long brandId,
        Long priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Long priority,
        Money money) {
}
