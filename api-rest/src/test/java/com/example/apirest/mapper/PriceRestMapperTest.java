package com.example.apirest.mapper;

import com.example.api.dto.PriceResponse;
import com.example.domain.entity.Price;
import com.example.domain.vo.Money;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PriceRestMapperTest {

    private final PriceRestMapper mapper = Mappers.getMapper(PriceRestMapper.class);

    @Test
    void toDto_ShouldMapAllFieldsCorrectly_WhenPriceHasData() {

        BigDecimal amount = new BigDecimal("9.99");
        String currency = "EUR";

        Price price = new Price(
                1L,
                100L,
                2L,
                1L,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(10),
                1L,
                new Money(amount, currency)
        );

        PriceResponse response = mapper.toDto(price);

        assertNotNull(response);
        assertEquals(amount, response.getPrice());
        assertEquals(currency, response.getCurrency());
    }

    @Test
    void toDto_ShouldHandleNullMoneyObject_WithoutThrowingException() {

        Price price = new Price(
                1L,
                100L,
                2L,
                1L,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(10),
                1L,
                null
        );

        PriceResponse response = mapper.toDto(price);

        assertNotNull(response);
        assertNull(response.getPrice());
        assertNull(response.getCurrency());
    }
}
