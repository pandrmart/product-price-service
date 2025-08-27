package com.example.productprice.domain.vo;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriceTest {

    @Test
    void priceRecord_shouldBeCreatedCorrectly_withValidArguments() {

        BigDecimal validAmount = new BigDecimal("10.50");
        String validCurrency = "EUR";

        Price price = new Price(validAmount, validCurrency);

        assertNotNull(price);
        assertEquals(validAmount, price.amount());
        assertEquals(validCurrency, price.currency());
    }

    @Test
    void priceRecord_shouldAllowZeroAmount() {

        Price price = new Price(BigDecimal.ZERO, "USD");

        assertNotNull(price);
        assertEquals(BigDecimal.ZERO, price.amount());
    }

    @Test
    void priceRecord_shouldThrowException_whenAmountIsNull() {

        BigDecimal invalidAmount = null;

        assertThrows(NullPointerException.class, () -> new Price(invalidAmount, "EUR"));
    }

    @Test
    void priceRecord_shouldThrowException_whenCurrencyIsNull() {

        String invalidCurrency = null;

        assertThrows(NullPointerException.class, () -> new Price(new BigDecimal("10.00"), invalidCurrency));
    }

    @Test
    void priceRecord_shouldThrowException_whenAmountIsNegative() {

        BigDecimal negativeAmount = new BigDecimal("-5.00");

        assertThrows(IllegalArgumentException.class, () -> new Price(negativeAmount, "USD"));
    }

    @Test
    void priceRecord_shouldThrowException_whenCurrencyLengthIsNotThree() {

        String invalidCurrency = "EU";

        assertThrows(IllegalArgumentException.class, () -> new Price(new BigDecimal("10.00"), invalidCurrency));
    }
}
