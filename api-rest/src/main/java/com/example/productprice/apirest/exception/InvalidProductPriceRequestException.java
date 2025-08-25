package com.example.productprice.apirest.exception;

import java.time.LocalDateTime;

public class InvalidProductPriceRequestException extends RuntimeException {

    public InvalidProductPriceRequestException(String message) {
        super(message);
    }

    public InvalidProductPriceRequestException(Long productId, Long brandId, LocalDateTime applicationDate) {
        super(String.format("Invalid request: productId=%d, brandId=%d, applicationDate=%s", productId, brandId, applicationDate));
    }
}
