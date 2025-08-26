package com.example.productprice.domain.entity;

import com.example.productprice.domain.vo.Price;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductPriceTest {

    @Test
    void productPriceRecord_ShouldBeInstantiatedCorrectly() {

        Long productId = 100L;
        Long brandId = 2L;
        Long priceList = 1L;
        LocalDateTime startDate = LocalDateTime.of(2025, 8, 20, 10, 0);
        LocalDateTime endDate = LocalDateTime.of(2025, 8, 21, 10, 0);
        Long priority = 1L;

        Price price = new Price(new BigDecimal("12.34"), "EUR");

        ProductPrice productPrice = new ProductPrice(productId, brandId, priceList, startDate, endDate, priority, price);

        assertNotNull(productPrice);
        assertEquals(productId, productPrice.productId());
        assertEquals(brandId, productPrice.brandId());
        assertEquals(priceList, productPrice.priceList());
        assertEquals(startDate, productPrice.startDate());
        assertEquals(endDate, productPrice.endDate());
        assertEquals(priority, productPrice.priority());
        assertEquals(price, productPrice.price());
    }
}
