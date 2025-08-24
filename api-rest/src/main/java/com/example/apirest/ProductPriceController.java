package com.example.apirest;

import com.example.api.controller.ProductPriceApi;
import com.example.api.dto.ProductPriceResponse;
import com.example.apirest.mapper.ProductPriceRestMapper;
import com.example.domain.port.in.GetProductPriceUseCase;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ProductPriceController implements ProductPriceApi {

    private final GetProductPriceUseCase getProductPriceUseCase;
    private final ProductPriceRestMapper mapper;

    public ProductPriceController(GetProductPriceUseCase getProductPriceUseCase, ProductPriceRestMapper productPriceRestMapper) {
        this.getProductPriceUseCase = getProductPriceUseCase;
        this.mapper = productPriceRestMapper;
    }

    @Override
    public ResponseEntity<ProductPriceResponse> getProductPrice(@RequestParam Long productId,
                                                                @RequestParam Long brandId,
                                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {
        return getProductPriceUseCase.getProductPrice(productId, brandId, applicationDate).map(mapper::toDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
