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
 * Application service that implements the {@link GetProductPriceUseCase}.
 * <p>
 * This class represents the application layer for retrieving a {@link ProductPrice}.
 * It validates input parameters, invokes the {@link GetProductPricePort} to access
 * the persistence layer, and applies business rules such as throwing an exception
 * when no matching product price is found.
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
