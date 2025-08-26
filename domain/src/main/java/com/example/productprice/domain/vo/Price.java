package com.example.productprice.domain.vo;

import java.math.BigDecimal;
import java.util.Objects;

public record Price(BigDecimal amount, String currency) {

    public Price {
        Objects.requireNonNull(amount, "Field amount should not be null");
        Objects.requireNonNull(currency, "Field currency should not be null");
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Field amount should not be negative");
        }
        if (currency.length() != 3) {
            throw new IllegalArgumentException("Currency length should be 3 chars");
        }
    }
}
