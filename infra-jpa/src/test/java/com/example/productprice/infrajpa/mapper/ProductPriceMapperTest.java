package com.example.productprice.infrajpa.mapper;

import com.example.productprice.domain.entity.ProductPrice;
import com.example.productprice.infrajpa.entity.ProductPriceEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductPriceMapperTest {

    private final ProductPriceMapper mapper = Mappers.getMapper(ProductPriceMapper.class);

    @Test
    void toDomain_shouldMapAllFieldsCorrectly_withValidData() {

        ProductPriceEntity entity = new ProductPriceEntity();
        entity.setId(1L);
        entity.setProductId(100L);
        entity.setBrandId(2L);
        entity.setPriceList(1L);
        entity.setStartDate(LocalDateTime.of(2025, 8, 20, 10, 0, 0));
        entity.setEndDate(LocalDateTime.of(2025, 8, 21, 10, 0, 0));
        entity.setPriority(1L);
        entity.setPrice(new BigDecimal("9.99"));
        entity.setCurrency("EUR");

        ProductPrice domainRecord = mapper.toDomain(entity);

        assertNotNull(domainRecord);
        assertEquals(entity.getProductId(), domainRecord.productId());
        assertEquals(entity.getBrandId(), domainRecord.brandId());
        assertEquals(entity.getPriceList(), domainRecord.priceList());
        assertEquals(entity.getStartDate(), domainRecord.startDate());
        assertEquals(entity.getEndDate(), domainRecord.endDate());
        assertEquals(entity.getPriority(), domainRecord.priority());

        assertNotNull(domainRecord.price());
        assertEquals(entity.getPrice(), domainRecord.price().amount());
        assertEquals(entity.getCurrency(), domainRecord.price().currency());
    }

    @Test
    void toDomain_shouldThrowException_whenMoneyFieldsAreInvalid() {

        ProductPriceEntity entity = new ProductPriceEntity();
        entity.setPrice(null);

        assertThrows(NullPointerException.class, () -> mapper.toDomain(entity));
    }

    @Test
    void toDomain_shouldMapCorrectly_withMinimalEntity() {

        ProductPriceEntity entity = new ProductPriceEntity();
        entity.setProductId(100L);
        entity.setBrandId(1L);
        entity.setPriceList(1L);
        entity.setStartDate(LocalDateTime.of(2025, 8, 20, 10, 0));
        entity.setEndDate(LocalDateTime.of(2025, 8, 21, 10, 0));
        entity.setPriority(1L);
        entity.setPrice(new BigDecimal("10.00"));
        entity.setCurrency("USD");

        ProductPrice domain = mapper.toDomain(entity);

        assertNotNull(domain);
        assertEquals(entity.getProductId(), domain.productId());
        assertEquals(entity.getBrandId(), domain.brandId());
        assertEquals(entity.getPriceList(), domain.priceList());
        assertEquals(entity.getStartDate(), domain.startDate());
        assertEquals(entity.getEndDate(), domain.endDate());
        assertEquals(entity.getPriority(), domain.priority());
        assertNotNull(domain.price());
        assertEquals(entity.getPrice(), domain.price().amount());
        assertEquals(entity.getCurrency(), domain.price().currency());
    }
}
