package com.example.productprice.domain.entity;

import com.example.productprice.domain.vo.Price;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public record ProductPrice(
        Long productId,
        Long brandId,
        Long priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Long priority,
        Price price) {

    public ProductPrice {

        Objects.requireNonNull(productId, "Field productId should not be null");
        Objects.requireNonNull(brandId, "Field brandId should not be null");
        Objects.requireNonNull(priceList, "Field priceList should not be null");
        Objects.requireNonNull(startDate, "Field startDate should not be null");
        Objects.requireNonNull(endDate, "Field endDate should not be null");
        Objects.requireNonNull(priority, "Field priority should not be null");
        Objects.requireNonNull(price, "Field price should not be null");

        Optional.of(productId)
                .filter(id -> id > 0)
                .orElseThrow(() -> new IllegalArgumentException("Field productId should be greater than 0"));

        Optional.of(brandId)
                .filter(id -> id > 0)
                .orElseThrow(() -> new IllegalArgumentException("Field brandId should be greater than 0"));

        Optional.of(endDate)
                .filter(ed -> !ed.isBefore(startDate))
                .orElseThrow(() -> new IllegalArgumentException("Field endDate should be after startDate"));
    }
}
