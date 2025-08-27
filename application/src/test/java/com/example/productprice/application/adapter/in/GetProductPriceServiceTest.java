package com.example.productprice.application.adapter.in;

import com.example.productprice.domain.entity.ProductPrice;
import com.example.productprice.domain.exception.ProductPriceNotFoundException;
import com.example.productprice.domain.port.out.GetProductPricePort;
import com.example.productprice.domain.vo.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetProductPriceServiceTest {

    @Mock
    private GetProductPricePort getProductPricePort;

    @InjectMocks
    private GetProductPriceService getPriceService;

    private static final Long VALID_PRODUCT_ID = 100L;
    private static final Long VALID_BRAND_ID = 2L;
    private static final LocalDateTime VALID_DATE = LocalDateTime.now();

    @Test
    void getProductPrice_ShouldReturnProductPrice_WhenPortFindsIt() {

        Price price = new Price(new BigDecimal("12.34"), "EUR");

        ProductPrice expectedProductPrice = new ProductPrice(
                VALID_PRODUCT_ID, VALID_BRAND_ID, 1L,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(1),
                1L, price
        );

        when(getProductPricePort.getProductPrice(VALID_PRODUCT_ID, VALID_BRAND_ID, VALID_DATE))
                .thenReturn(Optional.of(expectedProductPrice));

        ProductPrice actualPrice = getPriceService.getProductPrice(VALID_PRODUCT_ID, VALID_BRAND_ID, VALID_DATE);

        assertEquals(expectedProductPrice, actualPrice);

        verify(getProductPricePort).getProductPrice(VALID_PRODUCT_ID, VALID_BRAND_ID, VALID_DATE);
    }

    @Test
    void getProductPrice_ShouldThrowProductPriceNotFoundException_WhenPortReturnsEmptyOptional() {

        when(getProductPricePort.getProductPrice(VALID_PRODUCT_ID, VALID_BRAND_ID, VALID_DATE))
                .thenReturn(Optional.empty());

        assertThrows(ProductPriceNotFoundException.class,
                () -> getPriceService.getProductPrice(VALID_PRODUCT_ID, VALID_BRAND_ID, VALID_DATE)
        );

        verify(getProductPricePort).getProductPrice(VALID_PRODUCT_ID, VALID_BRAND_ID, VALID_DATE);
    }

    @ParameterizedTest(name = "{index} => productId={0}, brandId={1}, date={2}")
    @MethodSource("invalidParameters")
    void getProductPrice_ShouldThrowIllegalArgumentException_ForInvalidParameters(
            Long productId, Long brandId, LocalDateTime applicationDate) {
        assertThrows(IllegalArgumentException.class,
                () -> getPriceService.getProductPrice(productId, brandId, applicationDate));
    }

    private static Stream<Object[]> invalidParameters() {
        LocalDateTime now = LocalDateTime.now();
        return Stream.of(
                new Object[]{null, VALID_BRAND_ID, now},
                new Object[]{0L, VALID_BRAND_ID, now},
                new Object[]{-1L, VALID_BRAND_ID, now},
                new Object[]{VALID_PRODUCT_ID, null, now},
                new Object[]{VALID_PRODUCT_ID, 0L, now},
                new Object[]{VALID_PRODUCT_ID, -5L, now},
                new Object[]{VALID_PRODUCT_ID, VALID_BRAND_ID, null}
        );
    }
}
