package com.example.domain.port.out;

import com.example.domain.entity.ProductPrice;

import java.time.LocalDateTime;
import java.util.Optional;

public interface GetProductPricePort {

    Optional<ProductPrice> getProductPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
