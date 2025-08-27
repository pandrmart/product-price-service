package com.example.productprice.domain.port.in;

import com.example.productprice.domain.entity.ProductPrice;
import com.example.productprice.domain.exception.ProductPriceNotFoundException;

import java.time.LocalDateTime;

/**
 * Input port for retrieving the applicable {@link ProductPrice}.
 * <p>
 * Defines the public contract for the application's use case of obtaining a product price.
 * Implementations handle the application logic and enforce rules such as throwing an
 * exception when no matching price is found.
 */
public interface GetProductPriceUseCase {

    /**
     * Retrieves the applicable {@link ProductPrice} based on the given criteria.
     * <p>
     * Implementations are responsible for applying application rules and accessing
     * the necessary ports to retrieve the data.
     *
     * @param productId       The unique identifier of the product.
     * @param brandId         The unique identifier of the brand.
     * @param applicationDate The date and time for which the price is requested.
     * @return The found {@link ProductPrice} matching the criteria.
     * @throws ProductPriceNotFoundException if no applicable price is found.
     */
    ProductPrice getProductPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
