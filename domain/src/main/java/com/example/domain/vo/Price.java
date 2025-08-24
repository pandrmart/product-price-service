package com.example.domain.vo;

import java.math.BigDecimal;
import java.util.Objects;

public record Price(BigDecimal amount, String currency) {

    public Price {
        Objects.requireNonNull(amount, "Amount should not be null");
        Objects.requireNonNull(currency, "Currency should not be null");
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (currency.length() != 3) {
            throw new IllegalArgumentException("Currency length should be 3 chars");
        }
    }
}
