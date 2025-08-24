package com.example.application.adapter.in;

import com.example.domain.entity.ProductPrice;
import com.example.domain.exception.ProductPriceNotFoundException;
import com.example.domain.port.in.GetProductPriceUseCase;
import com.example.domain.port.out.GetProductPricePort;

import java.time.LocalDateTime;

public class GetProductPriceService implements GetProductPriceUseCase {

    private final GetProductPricePort getProductPricePort;

    public GetProductPriceService(GetProductPricePort getProductPricePort) {
        this.getProductPricePort = getProductPricePort;
    }

    @Override
    public ProductPrice getProductPrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        return getProductPricePort.
                getProductPrice(productId, brandId, applicationDate)
                .orElseThrow(() -> new ProductPriceNotFoundException(productId, brandId, applicationDate));
    }
}
