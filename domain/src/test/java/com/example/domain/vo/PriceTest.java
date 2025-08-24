package com.example.domain.vo;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriceTest {

    @Test
    void moneyRecord_shouldBeCreatedCorrectly_withValidArguments() {

        BigDecimal validAmount = new BigDecimal("10.50");
        String validCurrency = "EUR";

        Price price = new Price(validAmount, validCurrency);

        assertNotNull(price);
        assertEquals(validAmount, price.amount());
        assertEquals(validCurrency, price.currency());
    }

    @Test
    void moneyRecord_shouldThrowException_whenAmountIsNull() {

        BigDecimal invalidAmount = null;

        assertThrows(NullPointerException.class, () -> new Price(invalidAmount, "EUR"));
    }

    @Test
    void moneyRecord_shouldThrowException_whenCurrencyIsNull() {

        String invalidCurrency = null;

        assertThrows(NullPointerException.class, () -> new Price(new BigDecimal("10.00"), invalidCurrency));
    }

    @Test
    void moneyRecord_shouldThrowException_whenAmountIsNegative() {

        BigDecimal negativeAmount = new BigDecimal("-5.00");

        assertThrows(IllegalArgumentException.class, () -> new Price(negativeAmount, "USD"));
    }

    @Test
    void moneyRecord_shouldThrowException_whenCurrencyLengthIsNotThree() {

        String invalidCurrency = "EU";

        assertThrows(IllegalArgumentException.class, () -> new Price(new BigDecimal("10.00"), invalidCurrency));
    }
}
