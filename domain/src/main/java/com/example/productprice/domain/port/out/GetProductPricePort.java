package com.example.productprice.domain.port.out;

import com.example.productprice.domain.entity.ProductPrice;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Port to access persistence for ProductPrice entities.
 * This contract is implemented by a persistence adapter, a JPA repository.
 */
public interface GetProductPricePort {

    /**
     * Finds the applicable product price in the persistence layer.
     *
     * @param productId The unique identifier of the product.
     * @param brandId The unique identifier of the brand.
     * @param applicationDate The date and time to find the valid price.
     * @return An Optional containing the found ProductPrice, or empty if none is found.
     */
    Optional<ProductPrice> getProductPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
