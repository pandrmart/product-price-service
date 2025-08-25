package com.example.productprice.application.adapter.in;

import com.example.productprice.domain.entity.ProductPrice;
import com.example.productprice.domain.exception.ProductPriceNotFoundException;
import com.example.productprice.domain.port.out.GetProductPricePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class GetProductPriceServiceTest {

    @Mock
    private GetProductPricePort getProductPricePort;

    @InjectMocks
    private GetProductPriceService getPriceService;

    private final Long productId = 100L;
    private final Long brandId = 2L;
    private final LocalDateTime applicationDate = LocalDateTime.now();

    @Test
    void getProductPrice_ShouldReturnProductPrice_WhenPortFindsIt() {

        ProductPrice expectedProductPrice = new ProductPrice(
                1L, productId, brandId, 1L,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(1),
                1L, null
        );

        when(getProductPricePort.getProductPrice(productId, brandId, applicationDate))
                .thenReturn(Optional.of(expectedProductPrice));

        ProductPrice actualPrice = getPriceService.getProductPrice(productId, brandId, applicationDate);

        assertEquals(expectedProductPrice, actualPrice);

        verify(getProductPricePort).getProductPrice(productId, brandId, applicationDate);
    }

    @Test
    void getProductPrice_ShouldReturnEmptyOptional_WhenPortDoesNotFindProductPrice() {

        when(getProductPricePort.getProductPrice(productId, brandId, applicationDate))
                .thenReturn(Optional.empty());

        assertThrows(
                ProductPriceNotFoundException.class,
                () -> getPriceService.getProductPrice(productId, brandId, applicationDate)
        );

        verify(getProductPricePort).getProductPrice(productId, brandId, applicationDate);
    }

    @Test
    void getProductPrice_ShouldThrowProductPriceNotFoundException_WhenPortReturnsEmptyOptional() {

        when(getProductPricePort.getProductPrice(productId, brandId, applicationDate))
                .thenReturn(Optional.empty());

        assertThrows(ProductPriceNotFoundException.class,
                () -> getPriceService.getProductPrice(productId, brandId, applicationDate)
        );

        verify(getProductPricePort).getProductPrice(productId, brandId, applicationDate);
    }
}
