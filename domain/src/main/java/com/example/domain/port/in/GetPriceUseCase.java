package com.example.domain.port.in;

import com.example.domain.entity.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface GetPriceUseCase {

    Optional<Price> getPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
