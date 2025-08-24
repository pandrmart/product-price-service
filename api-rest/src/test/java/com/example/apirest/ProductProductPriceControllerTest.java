package com.example.apirest;

import com.example.api.dto.ProductPriceResponse;
import com.example.apirest.mapper.ProductPriceRestMapper;
import com.example.domain.entity.ProductPrice;
import com.example.domain.port.in.GetProductPriceUseCase;
import com.example.domain.vo.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductProductPriceControllerTest {

    @Mock
    private GetProductPriceUseCase getProductPriceUseCase;

    @Mock
    private ProductPriceRestMapper mapper;

    @InjectMocks
    private ProductPriceController productPriceController;

    private Long testProductId;
    private Long testBrandId;
    private LocalDateTime testApplicationDate;
    private ProductPrice testProductPriceDomain;
    private ProductPriceResponse testProductPriceResponse;

    @BeforeEach
    void setUp() {

        testProductId = 100L;
        testBrandId = 1L;
        testApplicationDate = LocalDateTime.of(2025, 8, 20, 10, 0, 0);

        Price testPrice = new Price(new BigDecimal("25.00"), "EUR");

        testProductPriceDomain = new ProductPrice(
                1L, testProductId, testBrandId, 1L,
                LocalDateTime.of(2025, 8, 19, 0, 0, 0),
                LocalDateTime.of(2025, 8, 21, 23, 59, 59),
                1L, testPrice
        );

        testProductPriceResponse = new ProductPriceResponse();
        testProductPriceResponse.setPrice(testPrice.amount());
        testProductPriceResponse.setCurrency(testPrice.currency());
    }

    @Test
    void searchPrices_ShouldReturnOkWithPrice_WhenPriceFound() {

        when(getProductPriceUseCase.getProductPrice(testProductId, testBrandId, testApplicationDate))
                .thenReturn(Optional.of(testProductPriceDomain));

        when(mapper.toDto(testProductPriceDomain))
                .thenReturn(testProductPriceResponse);

        ResponseEntity<ProductPriceResponse> responseEntity = productPriceController.getProductPrice(
                testProductId, testBrandId, testApplicationDate);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(testProductPriceResponse, responseEntity.getBody());
    }

    @Test
    void searchPrices_ShouldReturnNotFound_WhenPriceNotFound() {

        when(getProductPriceUseCase.getProductPrice(testProductId, testBrandId, testApplicationDate))
                .thenReturn(Optional.empty());

        ResponseEntity<ProductPriceResponse> responseEntity = productPriceController.getProductPrice(
                testProductId, testBrandId, testApplicationDate);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
}