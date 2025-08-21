package com.example.apirest;

import com.example.api.controller.PriceApi;
import com.example.api.dto.PriceRequest;
import com.example.api.dto.PriceResponse;
import com.example.apirest.mapper.PriceRestMapper;
import com.example.domain.port.in.GetPriceUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceController implements PriceApi {

    private final GetPriceUseCase getPriceUseCase;
    private final PriceRestMapper mapper;

    public PriceController(GetPriceUseCase getPriceUseCase, PriceRestMapper priceRestMapper) {
        this.getPriceUseCase = getPriceUseCase;
        this.mapper = priceRestMapper;
    }

    @Override
    public ResponseEntity<PriceResponse> searchPrices(PriceRequest request) {
        return getPriceUseCase.getPrice(request.getProductId(), request.getBrandId(), request.getApplicationDate()).map(mapper::toDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
