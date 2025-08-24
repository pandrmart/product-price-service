package com.example.domain.port.in;

import com.example.domain.entity.ProductPrice;

import java.time.LocalDateTime;

public interface GetProductPriceUseCase {

    ProductPrice getProductPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
