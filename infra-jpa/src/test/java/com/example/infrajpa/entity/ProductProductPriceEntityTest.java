package com.example.infrajpa.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ProductProductPriceEntityTest {

    @Test
    void priceEntity_shouldSetAndGetFieldsCorrectly() {

        ProductPriceEntity productPriceEntity = new ProductPriceEntity();

        Long id = 1L;
        Long brandId = 2L;
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        Long priceList = 10L;
        Long productId = 100L;
        Long priority = 1L;
        BigDecimal price = new BigDecimal("25.50");
        String currency = "EUR";

        productPriceEntity.setId(id);
        productPriceEntity.setBrandId(brandId);
        productPriceEntity.setStartDate(startDate);
        productPriceEntity.setEndDate(endDate);
        productPriceEntity.setPriceList(priceList);
        productPriceEntity.setProductId(productId);
        productPriceEntity.setPriority(priority);
        productPriceEntity.setPrice(price);
        productPriceEntity.setCurrency(currency);

        assertNotNull(productPriceEntity);
        assertEquals(id, productPriceEntity.getId());
        assertEquals(brandId, productPriceEntity.getBrandId());
        assertEquals(startDate, productPriceEntity.getStartDate());
        assertEquals(endDate, productPriceEntity.getEndDate());
        assertEquals(priceList, productPriceEntity.getPriceList());
        assertEquals(productId, productPriceEntity.getProductId());
        assertEquals(priority, productPriceEntity.getPriority());
        assertEquals(price, productPriceEntity.getPrice());
        assertEquals(currency, productPriceEntity.getCurrency());
    }

    @Test
    void priceEntity_shouldBeEqual_whenIdsAreSame() {

        Long sharedId = 1L;
        ProductPriceEntity entity1 = new ProductPriceEntity();
        entity1.setId(sharedId);
        entity1.setProductId(100L);

        ProductPriceEntity entity2 = new ProductPriceEntity();
        entity2.setId(sharedId);
        entity2.setProductId(200L);

        assertEquals(entity1, entity2);
        assertEquals(entity1.hashCode(), entity2.hashCode());
    }

    @Test
    void priceEntity_shouldNotBeEqual_whenIdsAreDifferent() {

        ProductPriceEntity entity1 = new ProductPriceEntity();
        entity1.setId(1L);

        ProductPriceEntity entity2 = new ProductPriceEntity();
        entity2.setId(2L);

        assertNotEquals(entity1, entity2);
        assertNotEquals(entity1.hashCode(), entity2.hashCode());
    }
}
