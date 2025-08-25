package com.example.productprice.infrajpa.adapter.out;

import com.example.productprice.domain.entity.ProductPrice;
import com.example.productprice.infrajpa.entity.ProductPriceEntity;
import com.example.productprice.infrajpa.mapper.ProductPriceMapper;
import com.example.productprice.infrajpa.repository.ProductPriceRepository;
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
public class GetProductPriceAdapterTest {

    @Mock
    private ProductPriceRepository productPriceRepository;

    @Mock
    private ProductPriceMapper mapper;

    @InjectMocks
    private GetProductProductPriceAdapter getProductPriceAdapter;

    private final Long testProductId = 100L;
    private final Long testBrandId = 2L;
    private final LocalDateTime testApplicationDate = LocalDateTime.of(2025, 8, 21, 10, 0, 0);

    private ProductPriceEntity testProductPriceEntity;
    private ProductPrice testProductPriceDomain;

    @BeforeEach
    void setUp() {

        testProductPriceEntity = new ProductPriceEntity();
        testProductPriceEntity.setId(1L);
        testProductPriceEntity.setProductId(testProductId);
        testProductPriceEntity.setBrandId(testBrandId);

        testProductPriceDomain = new ProductPrice(
                1L, testProductId, testBrandId, 1L,
                LocalDateTime.now(), LocalDateTime.now(), 1L, null
        );
    }

    @Test
    void getProductPrice_ShouldReturnMappedPrice_WhenRepositoryFindsIt() {

        when(productPriceRepository.findPrice(testProductId, testBrandId, testApplicationDate))
                .thenReturn(List.of(testProductPriceEntity));

        when(mapper.toDomain(testProductPriceEntity))
                .thenReturn(testProductPriceDomain);

        Optional<ProductPrice> result = getProductPriceAdapter.getProductPrice(testProductId, testBrandId, testApplicationDate);

        assertTrue(result.isPresent());
        assertEquals(testProductPriceDomain, result.get());

        verify(productPriceRepository, times(1)).findPrice(testProductId, testBrandId, testApplicationDate);
        verify(mapper, times(1)).toDomain(testProductPriceEntity);
    }

    @Test
    void getProductPrice_ShouldReturnEmptyOptional_WhenRepositoryFindsNothing() {

        when(productPriceRepository.findPrice(testProductId, testBrandId, testApplicationDate))
                .thenReturn(Collections.emptyList());

        Optional<ProductPrice> result = getProductPriceAdapter.getProductPrice(testProductId, testBrandId, testApplicationDate);

        assertTrue(result.isEmpty());

        verify(mapper, times(0)).toDomain(testProductPriceEntity);
    }
}
