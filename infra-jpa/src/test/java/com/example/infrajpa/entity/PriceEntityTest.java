package com.example.infrajpa.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PriceEntityTest {

    @Test
    void priceEntity_shouldSetAndGetFieldsCorrectly() {

        PriceEntity priceEntity = new PriceEntity();

        Long id = 1L;
        Long brandId = 2L;
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        Long priceList = 10L;
        Long productId = 100L;
        Long priority = 1L;
        BigDecimal price = new BigDecimal("25.50");
        String currency = "EUR";

        priceEntity.setId(id);
        priceEntity.setBrandId(brandId);
        priceEntity.setStartDate(startDate);
        priceEntity.setEndDate(endDate);
        priceEntity.setPriceList(priceList);
        priceEntity.setProductId(productId);
        priceEntity.setPriority(priority);
        priceEntity.setPrice(price);
        priceEntity.setCurrency(currency);

        assertNotNull(priceEntity);
        assertEquals(id, priceEntity.getId());
        assertEquals(brandId, priceEntity.getBrandId());
        assertEquals(startDate, priceEntity.getStartDate());
        assertEquals(endDate, priceEntity.getEndDate());
        assertEquals(priceList, priceEntity.getPriceList());
        assertEquals(productId, priceEntity.getProductId());
        assertEquals(priority, priceEntity.getPriority());
        assertEquals(price, priceEntity.getPrice());
        assertEquals(currency, priceEntity.getCurrency());
    }

    @Test
    void priceEntity_shouldBeEqual_whenIdsAreSame() {

        Long sharedId = 1L;
        PriceEntity entity1 = new PriceEntity();
        entity1.setId(sharedId);
        entity1.setProductId(100L);

        PriceEntity entity2 = new PriceEntity();
        entity2.setId(sharedId);
        entity2.setProductId(200L);

        assertEquals(entity1, entity2);
        assertEquals(entity1.hashCode(), entity2.hashCode());
    }

    @Test
    void priceEntity_shouldNotBeEqual_whenIdsAreDifferent() {

        PriceEntity entity1 = new PriceEntity();
        entity1.setId(1L);

        PriceEntity entity2 = new PriceEntity();
        entity2.setId(2L);

        assertNotEquals(entity1, entity2);
        assertNotEquals(entity1.hashCode(), entity2.hashCode());
    }
}
