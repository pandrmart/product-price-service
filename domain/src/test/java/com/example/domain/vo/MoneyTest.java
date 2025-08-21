package com.example.domain.vo;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {

    @Test
    void moneyRecord_shouldBeCreatedCorrectly_withValidArguments() {

        BigDecimal validAmount = new BigDecimal("10.50");
        String validCurrency = "EUR";

        Money money = new Money(validAmount, validCurrency);

        assertNotNull(money);
        assertEquals(validAmount, money.amount());
        assertEquals(validCurrency, money.currency());
    }

    @Test
    void moneyRecord_shouldThrowException_whenAmountIsNull() {

        BigDecimal invalidAmount = null;

        assertThrows(NullPointerException.class, () -> new Money(invalidAmount, "EUR"));
    }

    @Test
    void moneyRecord_shouldThrowException_whenCurrencyIsNull() {

        String invalidCurrency = null;

        assertThrows(NullPointerException.class, () -> new Money(new BigDecimal("10.00"), invalidCurrency));
    }

    @Test
    void moneyRecord_shouldThrowException_whenAmountIsNegative() {

        BigDecimal negativeAmount = new BigDecimal("-5.00");

        assertThrows(IllegalArgumentException.class, () -> new Money(negativeAmount, "USD"));
    }

    @Test
    void moneyRecord_shouldThrowException_whenCurrencyLengthIsNotThree() {

        String invalidCurrency = "EU";

        assertThrows(IllegalArgumentException.class, () -> new Money(new BigDecimal("10.00"), invalidCurrency));
    }
}
