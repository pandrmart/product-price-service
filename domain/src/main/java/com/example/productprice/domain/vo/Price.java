package com.example.productprice.domain.vo;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

public record Price(BigDecimal amount, String currency) {

    public Price {

        Objects.requireNonNull(amount, "Field amount should not be null");
        Objects.requireNonNull(currency, "Field currency should not be null");

        Optional.of(amount)
                .filter(a -> a.compareTo(BigDecimal.ZERO) >= 0)
                .orElseThrow(() -> new IllegalArgumentException("Field amount should not be negative"));

        Optional.of(currency)
                .filter(c -> c.length() == 3)
                .orElseThrow(() -> new IllegalArgumentException("Currency length should be 3 chars"));
    }
}
