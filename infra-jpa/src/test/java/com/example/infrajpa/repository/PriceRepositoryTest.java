package com.example.infrajpa.repository;

import com.example.infrajpa.entity.PriceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ContextConfiguration(classes = PriceRepositoryTest.TestConfig.class)
public class PriceRepositoryTest {

    @Autowired
    private PriceRepository priceRepository;

    private Long productId;
    private Long brandId;
    private LocalDateTime applicationDate;

    @Configuration
    @EnableJpaRepositories(basePackages = "com.example.infrajpa.repository")
    @EntityScan(basePackages = "com.example.infrajpa.entity")
    static class TestConfig {}

    @BeforeEach
    void setUp() {

        productId = 1L;
        brandId = 1L;
        applicationDate = LocalDateTime.of(2025, 8, 20, 10, 0, 0);

        priceRepository.deleteAll();

        PriceEntity price1 = new PriceEntity();
        price1.setProductId(productId);
        price1.setBrandId(brandId);
        price1.setStartDate(LocalDateTime.of(2025, 8, 1, 0, 0, 0));
        price1.setEndDate(LocalDateTime.of(2025, 8, 30, 0, 0, 0));
        price1.setPriority(1L);
        price1.setPrice(new BigDecimal("30.00"));
        price1.setCurrency("EUR");

        PriceEntity price2 = new PriceEntity();
        price2.setProductId(productId);
        price2.setBrandId(brandId);
        price2.setStartDate(LocalDateTime.of(2025, 8, 1, 0, 0, 0));
        price2.setEndDate(LocalDateTime.of(2025, 8, 30, 0, 0, 0));
        price2.setPriority(0L);
        price2.setPrice(new BigDecimal("25.00"));
        price2.setCurrency("EUR");

        priceRepository.saveAll(List.of(price1, price2));
    }

    @Test
    void findFirstByMethod_shouldReturnHighestPriorityPrice() {

        Optional<PriceEntity> foundPrice = priceRepository.findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                productId, brandId, applicationDate, applicationDate
        );

        assertTrue(foundPrice.isPresent());
        assertEquals(1L, foundPrice.get().getPriority());
        assertEquals(new BigDecimal("30.00"), foundPrice.get().getPrice());
    }

    @Test
    void findFirstByMethod_shouldReturnEmptyOptional_whenNoPriceMatches() {

        priceRepository.deleteAll();

        Optional<PriceEntity> foundPrice = priceRepository.findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                productId, brandId, applicationDate, applicationDate
        );

        assertTrue(foundPrice.isEmpty());
    }

    @Test
    void findPrice_shouldReturnAllPricesOrderedByPriority() {

        List<PriceEntity> foundPrices = priceRepository.findPrice(productId, brandId, applicationDate);

        assertEquals(2, foundPrices.size());
        assertEquals(1L, foundPrices.get(0).getPriority());
        assertEquals(new BigDecimal("30.00"), foundPrices.get(0).getPrice());
        assertEquals(0L, foundPrices.get(1).getPriority());
        assertEquals(new BigDecimal("25.00"), foundPrices.get(1).getPrice());
    }

    @Test
    void findPrice_shouldReturnEmptyList_whenNoPriceMatches() {

        priceRepository.deleteAll();

        List<PriceEntity> foundPrices = priceRepository.findPrice(productId, brandId, applicationDate);

        assertTrue(foundPrices.isEmpty());
    }
}
