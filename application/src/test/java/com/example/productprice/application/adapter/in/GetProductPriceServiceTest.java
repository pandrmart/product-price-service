package com.example.productprice.application.adapter.in;

import com.example.productprice.domain.entity.ProductPrice;
import com.example.productprice.domain.exception.ProductPriceNotFoundException;
import com.example.productprice.domain.port.out.GetProductPricePort;
import com.example.productprice.domain.vo.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

        Price price = new Price(new BigDecimal("12.34"), "EUR");

        ProductPrice expectedProductPrice = new ProductPrice(
                productId, brandId, 1L,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(1),
                1L, price
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

    @Test
    void getProductPrice_ShouldThrowIllegalArgumentException_WhenProductIdIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> getPriceService.getProductPrice(null, brandId, applicationDate));
    }

    @Test
    void getProductPrice_ShouldThrowIllegalArgumentException_WhenProductIdIsZeroOrNegative() {

        assertThrows(IllegalArgumentException.class,
                () -> getPriceService.getProductPrice(0L, brandId, applicationDate));

        assertThrows(IllegalArgumentException.class,
                () -> getPriceService.getProductPrice(-10L, brandId, applicationDate));
    }

    @Test
    void getProductPrice_ShouldThrowIllegalArgumentException_WhenBrandIdIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> getPriceService.getProductPrice(productId, null, applicationDate));
    }

    @Test
    void getProductPrice_ShouldThrowIllegalArgumentException_WhenBrandIdIsZeroOrNegative() {

        assertThrows(IllegalArgumentException.class,
                () -> getPriceService.getProductPrice(productId, 0L, applicationDate));

        assertThrows(IllegalArgumentException.class,
                () -> getPriceService.getProductPrice(productId, -5L, applicationDate));
    }

    @Test
    void getProductPrice_ShouldThrowIllegalArgumentException_WhenApplicationDateIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> getPriceService.getProductPrice(productId, brandId, null));
    }
}
