package com.example.productprice.apirest.mapper;

import com.example.api.dto.ProductPriceResponse;
import com.example.productprice.domain.entity.ProductPrice;
import com.example.productprice.domain.vo.Price;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ProductProductPriceRestMapperTest {

    private final ProductPriceRestMapper mapper = Mappers.getMapper(ProductPriceRestMapper.class);

    @Test
    void toDto_ShouldMapAllFieldsCorrectly_WhenPriceHasData() {

        BigDecimal amount = new BigDecimal("9.99");
        String currency = "EUR";

        ProductPrice productPrice = new ProductPrice(
                1L,
                100L,
                2L,
                1L,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(10),
                1L,
                new Price(amount, currency)
        );

        ProductPriceResponse response = mapper.toDto(productPrice);

        assertNotNull(response);
        assertEquals(amount, response.getPrice());
        assertEquals(currency, response.getCurrency());
    }

    @Test
    void toDto_ShouldHandleNullMoneyObject_WithoutThrowingException() {

        ProductPrice productPrice = new ProductPrice(
                1L,
                100L,
                2L,
                1L,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(10),
                1L,
                null
        );

        ProductPriceResponse response = mapper.toDto(productPrice);

        assertNotNull(response);
        assertNull(response.getPrice());
        assertNull(response.getCurrency());
    }
}
