package com.example.productprice.domain.port.out;

import com.example.productprice.domain.entity.ProductPrice;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Output port for accessing persistence of {@link ProductPrice} entities.
 * <p>
 * This interface defines the contract for retrieving product prices from the
 * persistence layer. It is typically implemented by an adapter, such as a JPA repository.
 */
public interface GetProductPricePort {

    /**
     * Finds the applicable {@link ProductPrice} in the persistence layer.
     *
     * @param productId       The unique identifier of the product.
     * @param brandId         The unique identifier of the brand.
     * @param applicationDate The date and time for which the price is requested.
     * @return An {@link Optional} containing the found {@link ProductPrice},
     *         or empty if no matching price is found.
     */
    Optional<ProductPrice> getProductPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
