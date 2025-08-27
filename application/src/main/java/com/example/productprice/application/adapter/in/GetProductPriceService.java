package com.example.productprice.application.adapter.in;

import com.example.productprice.domain.entity.ProductPrice;
import com.example.productprice.domain.exception.ProductPriceNotFoundException;
import com.example.productprice.domain.port.in.GetProductPriceUseCase;
import com.example.productprice.domain.port.out.GetProductPricePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Optional;

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

        Optional.ofNullable(productId)
                .filter(id -> id > 0)
                .orElseThrow(() -> new IllegalArgumentException("Request parameter productId must be min 1"));
        Optional.ofNullable(brandId)
                .filter(id -> id > 0)
                .orElseThrow(() -> new IllegalArgumentException("Request parameter brandId must be min 1"));
        Optional.ofNullable(applicationDate)
                .orElseThrow(() -> new IllegalArgumentException("Request parameter applicationDate is missing"));

        log.debug("Getting product price for productId={}, brandId={}, applicationDate={}",
                productId, brandId, applicationDate);

        return getProductPricePort.
                getProductPrice(productId, brandId, applicationDate)
                .orElseThrow(() -> new ProductPriceNotFoundException(productId, brandId, applicationDate));
    }
}
