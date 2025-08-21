package com.example.domain.entity;

import com.example.domain.vo.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PriceTest {

    private Long id;
    private Long productId;
    private Long brandId;
    private Long priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long priority;
    private Money money;

    @BeforeEach
    void setUp() {
        id = 1L;
        productId = 100L;
        brandId = 2L;
        priceList = 1L;
        startDate = LocalDateTime.of(2025, 8, 20, 10, 0);
        endDate = LocalDateTime.of(2025, 8, 21, 10, 0);
        priority = 1L;

        money = new Money(new BigDecimal("12.34"), "EUR");
    }

    @Test
    void priceRecord_ShouldBeInstantiatedCorrectly() {

        Price price = new Price(id, productId, brandId, priceList, startDate, endDate, priority, money);

        assertNotNull(price);
        assertEquals(id, price.id());
        assertEquals(productId, price.productId());
        assertEquals(brandId, price.brandId());
        assertEquals(priceList, price.priceList());
        assertEquals(startDate, price.startDate());
        assertEquals(endDate, price.endDate());
        assertEquals(priority, price.priority());
        assertEquals(money, price.money());
    }

    @Test
    void priceRecord_ShouldHandleEqualsAndHashCodeCorrectly() {

        Price price1 = new Price(id, productId, brandId, priceList, startDate, endDate, priority, money);
        Price price2 = new Price(id, productId, brandId, priceList, startDate, endDate, priority, money);

        assertEquals(price1, price2);
        assertEquals(price1.hashCode(), price2.hashCode());

        Price differentPrice = new Price(2L, productId, brandId, priceList, startDate, endDate, priority, money);

        assertNotEquals(price1, differentPrice);
        assertNotEquals(price1.hashCode(), differentPrice.hashCode());
    }
}
