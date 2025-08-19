package com.example.application.adapter.in;

import com.example.domain.entity.Price;
import com.example.domain.port.in.GetPriceUseCase;
import com.example.domain.port.out.GetPricePort;

import java.time.LocalDateTime;
import java.util.Optional;

public class GetPriceService implements GetPriceUseCase {

    private final GetPricePort getPricePort;

    public GetPriceService(GetPricePort getPricePort) {
        this.getPricePort = getPricePort;
    }

    @Override
    public Optional<Price> getPrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        return getPricePort.getPrice(productId, brandId, applicationDate);
    }
}
