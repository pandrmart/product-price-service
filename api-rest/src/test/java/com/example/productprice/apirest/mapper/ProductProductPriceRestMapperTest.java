package com.example.productprice.apirest.mapper;

import com.example.api.dto.ProductPriceResponse;
import com.example.productprice.domain.entity.ProductPrice;
import com.example.productprice.domain.vo.Price;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductProductPriceRestMapperTest {

    private final ProductPriceRestMapper mapper = Mappers.getMapper(ProductPriceRestMapper.class);

    @Test
    void toDto_ShouldMapAllFieldsCorrectly_WhenPriceHasData() {

        BigDecimal amount = new BigDecimal("9.99");
        String currency = "EUR";

        ProductPrice productPrice = new ProductPrice(
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
    void toDto_ShouldMapAllFieldsCorrectly() {

        BigDecimal amount = new BigDecimal("9.99");
        String currency = "EUR";

        LocalDateTime startDate = LocalDateTime.of(2025, 8, 26, 10, 0);
        LocalDateTime endDate = LocalDateTime.of(2025, 8, 27, 10, 0);

        ProductPrice productPrice = new ProductPrice(
                100L,
                2L,
                1L,
                startDate,
                endDate,
                1L,
                new Price(amount, currency)
        );

        ProductPriceResponse response = mapper.toDto(productPrice);

        assertNotNull(response);
        assertEquals(100L, response.getProductId());
        assertEquals(2L, response.getBrandId());
        assertEquals(1L, response.getPriceList());
        assertEquals(startDate, response.getStartDate());
        assertEquals(endDate, response.getEndDate());
        assertEquals(amount, response.getPrice());
        assertEquals(currency, response.getCurrency());
    }
}
