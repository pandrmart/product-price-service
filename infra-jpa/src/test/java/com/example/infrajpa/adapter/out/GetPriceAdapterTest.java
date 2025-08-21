package com.example.infrajpa.adapter.out;

import com.example.domain.entity.Price;
import com.example.infrajpa.entity.PriceEntity;
import com.example.infrajpa.mapper.PriceMapper;
import com.example.infrajpa.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class GetPriceAdapterTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceMapper mapper;

    @InjectMocks
    private GetPriceAdapter getPriceAdapter;

    private final Long testProductId = 100L;
    private final Long testBrandId = 2L;
    private final LocalDateTime testApplicationDate = LocalDateTime.of(2025, 8, 21, 10, 0, 0);

    private PriceEntity testPriceEntity;
    private Price testPriceDomain;

    @BeforeEach
    void setUp() {

        testPriceEntity = new PriceEntity();
        testPriceEntity.setId(1L);
        testPriceEntity.setProductId(testProductId);
        testPriceEntity.setBrandId(testBrandId);

        testPriceDomain = new Price(
                1L, testProductId, testBrandId, 1L,
                LocalDateTime.now(), LocalDateTime.now(), 1L, null
        );
    }

    @Test
    void getPrice_ShouldReturnMappedPrice_WhenRepositoryFindsIt() {

        when(priceRepository.findPrice(testProductId, testBrandId, testApplicationDate))
                .thenReturn(List.of(testPriceEntity));

        when(mapper.toDomain(testPriceEntity))
                .thenReturn(testPriceDomain);

        Optional<Price> result = getPriceAdapter.getPrice(testProductId, testBrandId, testApplicationDate);

        assertTrue(result.isPresent());
        assertEquals(testPriceDomain, result.get());

        verify(priceRepository, times(1)).findPrice(testProductId, testBrandId, testApplicationDate);
        verify(mapper, times(1)).toDomain(testPriceEntity);
    }

    @Test
    void getPrice_ShouldReturnEmptyOptional_WhenRepositoryFindsNothing() {

        when(priceRepository.findPrice(testProductId, testBrandId, testApplicationDate))
                .thenReturn(Collections.emptyList());

        Optional<Price> result = getPriceAdapter.getPrice(testProductId, testBrandId, testApplicationDate);

        assertTrue(result.isEmpty());

        verify(mapper, times(0)).toDomain(testPriceEntity);
    }
}
