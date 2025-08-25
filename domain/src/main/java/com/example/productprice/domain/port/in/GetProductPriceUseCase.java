package com.example.productprice.domain.port.in;

import com.example.productprice.domain.entity.ProductPrice;
import com.example.productprice.domain.exception.ProductPriceNotFoundException;

import java.time.LocalDateTime;

/**
 * Port to retrieve the applicable product price.
 * This is the public contract for the application's core business logic.
 */
public interface GetProductPriceUseCase {

    /**
     * Retrieves the applicable product price based on a set of criteria.
     * This method applies the business rules to find the correct price.
     *
     * @param productId The unique identifier of the product.
     * @param brandId The unique identifier of the brand.
     * @param applicationDate The date and time for which the price is requested.
     * @return The found ProductPrice matching the criteria.
     * @throws ProductPriceNotFoundException if no applicable price is found.
     */
    ProductPrice getProductPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
