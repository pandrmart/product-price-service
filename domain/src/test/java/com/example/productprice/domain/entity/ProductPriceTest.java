package com.example.productprice.domain.entity;

import com.example.productprice.domain.vo.Price;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void productPriceRecord_ShouldThrowException_WhenRequiredFieldsAreNull() {

        Price price = new Price(new BigDecimal("12.34"), "EUR");
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);

        assertThrows(NullPointerException.class, () ->
                new ProductPrice(null, 1L, 1L, startDate, endDate, 1L, price)
        );
        assertThrows(NullPointerException.class, () ->
                new ProductPrice(1L, null, 1L, startDate, endDate, 1L, price)
        );
        assertThrows(NullPointerException.class, () ->
                new ProductPrice(1L, 1L, null, startDate, endDate, 1L, price)
        );
        assertThrows(NullPointerException.class, () ->
                new ProductPrice(1L, 1L, 1L, null, endDate, 1L, price)
        );
        assertThrows(NullPointerException.class, () ->
                new ProductPrice(1L, 1L, 1L, startDate, null, 1L, price)
        );
        assertThrows(NullPointerException.class, () ->
                new ProductPrice(1L, 1L, 1L, startDate, endDate, null, price)
        );
        assertThrows(NullPointerException.class, () ->
                new ProductPrice(1L, 1L, 1L, startDate, endDate, 1L, null)
        );
    }

    @Test
    void productPriceRecord_ShouldThrowException_WhenIdsAreNonPositive() {

        Price price = new Price(new BigDecimal("12.34"), "EUR");
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);

        assertThrows(IllegalArgumentException.class, () ->
                new ProductPrice(0L, 1L, 1L, startDate, endDate, 1L, price)
        );
        assertThrows(IllegalArgumentException.class, () ->
                new ProductPrice(-1L, 1L, 1L, startDate, endDate, 1L, price)
        );
        assertThrows(IllegalArgumentException.class, () ->
                new ProductPrice(1L, 0L, 1L, startDate, endDate, 1L, price)
        );
        assertThrows(IllegalArgumentException.class, () ->
                new ProductPrice(1L, -1L, 1L, startDate, endDate, 1L, price)
        );
    }

    @Test
    void productPriceRecord_ShouldThrowException_WhenEndDateBeforeStartDate() {

        Price price = new Price(new BigDecimal("12.34"), "EUR");
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.minusDays(1);

        assertThrows(IllegalArgumentException.class, () ->
                new ProductPrice(1L, 1L, 1L, startDate, endDate, 1L, price)
        );
    }
}
