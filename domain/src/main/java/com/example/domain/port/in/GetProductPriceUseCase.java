package com.example.domain.port.in;

import com.example.domain.entity.ProductPrice;

import java.time.LocalDateTime;
import java.util.Optional;

public interface GetProductPriceUseCase {

    Optional<ProductPrice> getProductPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
