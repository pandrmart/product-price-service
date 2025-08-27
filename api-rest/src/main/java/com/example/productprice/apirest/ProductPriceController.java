package com.example.productprice.apirest;

import com.example.api.controller.ProductPriceApi;
import com.example.api.dto.ProductPriceResponse;
import com.example.productprice.apirest.mapper.ProductPriceRestMapper;
import com.example.productprice.domain.entity.ProductPrice;
import com.example.productprice.domain.port.in.GetProductPriceUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * REST controller for the Product Price API.
 * <p>
 * This class acts as the entry point for incoming HTTP requests related to
 * product price. It validates request parameters, delegates the business logic
 * to the application layer via {@link GetProductPriceUseCase}, and maps domain
 * entities to REST responses.
 */
@RestController
@Slf4j
public class ProductPriceController implements ProductPriceApi {

    private final GetProductPriceUseCase getProductPriceUseCase;
    private final ProductPriceRestMapper mapper;

    public ProductPriceController(
            GetProductPriceUseCase getProductPriceUseCase, ProductPriceRestMapper productPriceRestMapper) {
        this.getProductPriceUseCase = getProductPriceUseCase;
        this.mapper = productPriceRestMapper;
    }

    /**
     * Retrieves the applicable product price based on the given criteria.
     * <p>
     * This method validates request parameters, delegates the retrieval logic
     * to the use case, and maps the result into a REST response.
     *
     * @param productId       The unique identifier of the product.
     * @param brandId         The unique identifier of the brand.
     * @param applicationDate The date and time for which the price is requested.
     * @return The found ProductPrice matching the criteria.
     */
    @Override
    public ResponseEntity<ProductPriceResponse> getProductPrice(
            @RequestParam Long productId,
            @RequestParam Long brandId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {

        log.info("Request received with productId: {}, brandId: {}, applicationDate: {}",
                productId, brandId, applicationDate);

        ProductPrice productPrice = getProductPriceUseCase.getProductPrice(productId, brandId, applicationDate);
        log.info("Product price found and will be returned");

        return ResponseEntity.ok(mapper.toDto(productPrice));
    }
}
