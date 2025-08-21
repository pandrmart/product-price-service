package com.example.application.adapter.in;

import com.example.domain.entity.Price;
import com.example.domain.port.out.GetPricePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class GetPriceServiceTest {

    @Mock
    private GetPricePort getPricePort;

    @InjectMocks
    private GetPriceService getPriceService;

    private final Long productId = 100L;
    private final Long brandId = 2L;
    private final LocalDateTime applicationDate = LocalDateTime.now();

    @Test
    void getPrice_ShouldReturnPrice_WhenPortFindsIt() {

        Price expectedPrice = new Price(
                1L, productId, brandId, 1L,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(1),
                1L, null
        );

        when(getPricePort.getPrice(productId, brandId, applicationDate))
                .thenReturn(Optional.of(expectedPrice));

        Optional<Price> actualPrice = getPriceService.getPrice(productId, brandId, applicationDate);

        assertTrue(actualPrice.isPresent());
        assertEquals(expectedPrice, actualPrice.get());

        verify(getPricePort).getPrice(productId, brandId, applicationDate);
    }

    @Test
    void getPrice_ShouldReturnEmptyOptional_WhenPortDoesNotFindPrice() {

        when(getPricePort.getPrice(productId, brandId, applicationDate))
                .thenReturn(Optional.empty());

        Optional<Price> actualPrice = getPriceService.getPrice(productId, brandId, applicationDate);

        assertTrue(actualPrice.isEmpty());

        verify(getPricePort).getPrice(productId, brandId, applicationDate);
    }
}
