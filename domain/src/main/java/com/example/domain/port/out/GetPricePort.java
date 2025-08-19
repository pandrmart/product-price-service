package com.example.domain.port.out;

import com.example.domain.entity.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface GetPricePort {

    Optional<Price> getPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
