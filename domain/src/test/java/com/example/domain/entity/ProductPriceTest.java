package com.example.domain.entity;

import com.example.domain.vo.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ProductPriceTest {

    private Long id;
    private Long productId;
    private Long brandId;
    private Long priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long priority;
    private Price price;

    @BeforeEach
    void setUp() {
        id = 1L;
        productId = 100L;
        brandId = 2L;
        priceList = 1L;
        startDate = LocalDateTime.of(2025, 8, 20, 10, 0);
        endDate = LocalDateTime.of(2025, 8, 21, 10, 0);
        priority = 1L;

        price = new Price(new BigDecimal("12.34"), "EUR");
    }

    @Test
    void priceRecord_ShouldBeInstantiatedCorrectly() {

        ProductPrice productPrice = new ProductPrice(id, productId, brandId, priceList, startDate, endDate, priority, price);

        assertNotNull(productPrice);
        assertEquals(id, productPrice.id());
        assertEquals(productId, productPrice.productId());
        assertEquals(brandId, productPrice.brandId());
        assertEquals(priceList, productPrice.priceList());
        assertEquals(startDate, productPrice.startDate());
        assertEquals(endDate, productPrice.endDate());
        assertEquals(priority, productPrice.priority());
        assertEquals(price, productPrice.price());
    }

    @Test
    void priceRecord_ShouldHandleEqualsAndHashCodeCorrectly() {

        ProductPrice productPrice1 = new ProductPrice(id, productId, brandId, priceList, startDate, endDate, priority, price);
        ProductPrice productPrice2 = new ProductPrice(id, productId, brandId, priceList, startDate, endDate, priority, price);

        assertEquals(productPrice1, productPrice2);
        assertEquals(productPrice1.hashCode(), productPrice2.hashCode());

        ProductPrice differentProductPrice = new ProductPrice(2L, productId, brandId, priceList, startDate, endDate, priority, price);

        assertNotEquals(productPrice1, differentProductPrice);
        assertNotEquals(productPrice1.hashCode(), differentProductPrice.hashCode());
    }
}
