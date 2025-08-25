package com.example.application.adapter.in;

import com.example.domain.entity.ProductPrice;
import com.example.domain.exception.ProductPriceNotFoundException;
import com.example.domain.port.in.GetProductPriceUseCase;
import com.example.domain.port.out.GetProductPricePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * Use case implementation for retrieving a product price.
 * <p>
 * This class contains the core business logic. It orchestrates the flow of data
 * between the API layer and the persistence layer (via the port) and applies
 * business rules such as handling cases where no price is found.
 */
@Slf4j
@RequiredArgsConstructor
public class GetProductPriceService implements GetProductPriceUseCase {

    private final GetProductPricePort getProductPricePort;

    @Override
    public ProductPrice getProductPrice(Long productId, Long brandId, LocalDateTime applicationDate) {

        log.debug("Getting product price for product id {}", productId);

        return getProductPricePort.
                getProductPrice(productId, brandId, applicationDate)
                .orElseThrow(() -> new ProductPriceNotFoundException(productId, brandId, applicationDate));
    }
}
