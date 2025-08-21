package com.example.infrajpa.mapper;

import com.example.domain.entity.Price;
import com.example.infrajpa.entity.PriceEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriceMapperTest {

    private final PriceMapper mapper = Mappers.getMapper(PriceMapper.class);

    @Test
    void toDomain_shouldMapAllFieldsCorrectly_withValidData() {

        PriceEntity entity = new PriceEntity();
        entity.setId(1L);
        entity.setProductId(100L);
        entity.setBrandId(2L);
        entity.setPriceList(1L);
        entity.setStartDate(LocalDateTime.of(2025, 8, 20, 10, 0, 0));
        entity.setEndDate(LocalDateTime.of(2025, 8, 21, 10, 0, 0));
        entity.setPriority(1L);
        entity.setPrice(new BigDecimal("9.99"));
        entity.setCurrency("EUR");

        Price domainRecord = mapper.toDomain(entity);

        assertNotNull(domainRecord);
        assertEquals(entity.getId(), domainRecord.id());
        assertEquals(entity.getProductId(), domainRecord.productId());
        assertEquals(entity.getBrandId(), domainRecord.brandId());
        assertEquals(entity.getPriceList(), domainRecord.priceList());
        assertEquals(entity.getStartDate(), domainRecord.startDate());
        assertEquals(entity.getEndDate(), domainRecord.endDate());
        assertEquals(entity.getPriority(), domainRecord.priority());

        assertNotNull(domainRecord.money());
        assertEquals(entity.getPrice(), domainRecord.money().amount());
        assertEquals(entity.getCurrency(), domainRecord.money().currency());
    }

    @Test
    void toDomain_shouldThrowException_whenMoneyFieldsAreInvalid() {

        PriceEntity entity = new PriceEntity();
        entity.setPrice(null); // Campo nulo, el constructor de Money fallará

        assertThrows(NullPointerException.class, () -> mapper.toDomain(entity));
    }
}
