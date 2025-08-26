package com.example.productprice.domain.entity;

import com.example.productprice.domain.vo.Price;

import java.time.LocalDateTime;

public record ProductPrice(
        Long productId,
        Long brandId,
        Long priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Long priority,
        Price price) {
}
