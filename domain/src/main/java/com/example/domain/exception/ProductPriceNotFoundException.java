package com.example.domain.exception;

import java.time.LocalDateTime;

public class ProductPriceNotFoundException extends RuntimeException {

    public ProductPriceNotFoundException(Long productId, Long brandId, LocalDateTime applicationDate) {
        super(String.format("No applicable price found for productId=%d, brandId=%d, applicationDate=%s", productId, brandId, applicationDate));
    }
}
