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
 * Controller class for the Product Price API.
 * <p>
 * This class serves as the entry point for API requests related to product price.
 * It handles HTTP requests, validates input, and orchestrates the call to the
 * application's use case layer to retrieve the applicable price.
 */
@RestController
@Slf4j
public class ProductPriceController implements ProductPriceApi {

    private final GetProductPriceUseCase getProductPriceUseCase;
    private final ProductPriceRestMapper mapper;

    public ProductPriceController(GetProductPriceUseCase getProductPriceUseCase, ProductPriceRestMapper productPriceRestMapper) {
        this.getProductPriceUseCase = getProductPriceUseCase;
        this.mapper = productPriceRestMapper;
    }

    /**
     * Retrieves the applicable product price based on a set of criteria.
     * This method applies business rules to find the correct price for a product
     * at a specific brand and application date.
     *
     * @param productId         The unique identifier of the product.
     * @param brandId           The unique identifier of the brand.
     * @param applicationDate   The date and time of the application.
     * @return The found ProductPrice matching the criteria.
     */
    @Override
    public ResponseEntity<ProductPriceResponse> getProductPrice(@RequestParam Long productId,
                                                                @RequestParam Long brandId,
                                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {

        log.info("Request received with productId: {}, brandId: {}, applicationDate: {}", productId, brandId, applicationDate);

        ProductPrice productPrice = getProductPriceUseCase.getProductPrice(productId, brandId, applicationDate);
        log.info("Product price found and will be returned");

        return ResponseEntity.ok(mapper.toDto(productPrice));
    }
}
