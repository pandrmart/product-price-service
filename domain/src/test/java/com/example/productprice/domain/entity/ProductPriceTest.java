package com.example.productprice.domain.entity;

import com.example.productprice.domain.vo.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ProductPriceTest {

    private static final LocalDateTime START_DATE = LocalDateTime.of(2025, 8, 20, 10, 0);
    private static final LocalDateTime END_DATE = START_DATE.plusDays(1);
    private static final Price PRICE = new Price(new BigDecimal("12.34"), "EUR");

    @Test
    void productPriceRecord_ShouldBeInstantiatedCorrectly() {

        ProductPrice productPrice = new ProductPrice(
                100L, 2L, 1L, START_DATE, END_DATE, 1L, PRICE
        );

        assertNotNull(productPrice);
        assertEquals(100L, productPrice.productId());
        assertEquals(2L, productPrice.brandId());
        assertEquals(1L, productPrice.priceList());
        assertEquals(START_DATE, productPrice.startDate());
        assertEquals(END_DATE, productPrice.endDate());
        assertEquals(1L, productPrice.priority());
        assertEquals(PRICE, productPrice.price());
    }

    @ParameterizedTest(name = "{index} => productId={0}, brandId={1}, priceList={2}, startDate={3}, endDate={4}, " +
            "priority={5}, price={6}")
    @MethodSource("nullFieldsProvider")
    void productPriceRecord_ShouldThrowException_WhenRequiredFieldsAreNull(
            Long productId, Long brandId, Long priceList,
            LocalDateTime startDate, LocalDateTime endDate,
            Long priority, Price price) {
        assertThrows(NullPointerException.class,
                () -> new ProductPrice(productId, brandId, priceList, startDate, endDate, priority, price));
    }

    private static Stream<Object[]> nullFieldsProvider() {
        return Stream.of(
                new Object[]{null, 1L, 1L, START_DATE, END_DATE, 1L, PRICE},
                new Object[]{1L, null, 1L, START_DATE, END_DATE, 1L, PRICE},
                new Object[]{1L, 1L, null, START_DATE, END_DATE, 1L, PRICE},
                new Object[]{1L, 1L, 1L, null, END_DATE, 1L, PRICE},
                new Object[]{1L, 1L, 1L, START_DATE, null, 1L, PRICE},
                new Object[]{1L, 1L, 1L, START_DATE, END_DATE, null, PRICE},
                new Object[]{1L, 1L, 1L, START_DATE, END_DATE, 1L, null}
        );
    }

    @ParameterizedTest(name = "{index} => productId={0}, brandId={1}")
    @MethodSource("invalidIdsProvider")
    void productPriceRecord_ShouldThrowException_WhenIdsAreNonPositive(Long productId, Long brandId) {
        assertThrows(IllegalArgumentException.class,
                () -> new ProductPrice(productId, brandId, 1L, START_DATE, END_DATE, 1L, PRICE));
    }

    private static Stream<Object[]> invalidIdsProvider() {
        return Stream.of(
                new Object[]{0L, 1L},
                new Object[]{-1L, 1L},
                new Object[]{1L, 0L},
                new Object[]{1L, -1L}
        );
    }

    @Test
    void productPriceRecord_ShouldThrowException_WhenEndDateBeforeStartDate() {
        assertThrows(IllegalArgumentException.class, () ->
                new ProductPrice(1L, 1L, 1L, START_DATE, START_DATE.minusDays(1), 1L, PRICE)
        );
    }
}
