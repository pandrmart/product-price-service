package com.example.apirest;

import com.example.api.dto.PriceRequest;
import com.example.api.dto.PriceResponse;
import com.example.apirest.mapper.PriceRestMapper;
import com.example.domain.entity.Price;
import com.example.domain.port.in.GetPriceUseCase;
import com.example.domain.vo.Money;
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
class PriceControllerTest {

    @Mock
    private GetPriceUseCase getPriceUseCase;

    @Mock
    private PriceRestMapper mapper;

    @InjectMocks
    private PriceController priceController;

    private Long testProductId;
    private Long testBrandId;
    private LocalDateTime testApplicationDate;
    private PriceRequest testPriceRequest;
    private Price testPriceDomain;
    private PriceResponse testPriceResponse;

    @BeforeEach
    void setUp() {

        testProductId = 100L;
        testBrandId = 1L;
        testApplicationDate = LocalDateTime.of(2025, 8, 20, 10, 0, 0);

        testPriceRequest = new PriceRequest();
        testPriceRequest.setProductId(testProductId);
        testPriceRequest.setBrandId(testBrandId);
        testPriceRequest.setApplicationDate(testApplicationDate);

        Money testMoney = new Money(new BigDecimal("25.00"), "EUR");

        testPriceDomain = new Price(
                1L, testProductId, testBrandId, 1L,
                LocalDateTime.of(2025, 8, 19, 0, 0, 0),
                LocalDateTime.of(2025, 8, 21, 23, 59, 59),
                1L, testMoney
        );

        testPriceResponse = new PriceResponse();
        testPriceResponse.setPrice(testMoney.amount());
        testPriceResponse.setCurrency(testMoney.currency());
    }

    @Test
    void searchPrices_ShouldReturnOkWithPrice_WhenPriceFound() {

        when(getPriceUseCase.getPrice(testProductId, testBrandId, testApplicationDate))
                .thenReturn(Optional.of(testPriceDomain));

        when(mapper.toDto(testPriceDomain))
                .thenReturn(testPriceResponse);

        ResponseEntity<PriceResponse> responseEntity = priceController.searchPrices(testPriceRequest);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(testPriceResponse, responseEntity.getBody());
    }

    @Test
    void searchPrices_ShouldReturnNotFound_WhenPriceNotFound() {

        when(getPriceUseCase.getPrice(testProductId, testBrandId, testApplicationDate))
                .thenReturn(Optional.empty());

        ResponseEntity<PriceResponse> responseEntity = priceController.searchPrices(testPriceRequest);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
}