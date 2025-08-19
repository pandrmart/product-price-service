package com.example.apirest;

import com.example.apirest.dto.PriceRequestDTO;
import com.example.apirest.dto.PriceResponseDTO;
import com.example.apirest.mapper.PriceRestMapper;
import com.example.domain.port.in.GetPriceUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final GetPriceUseCase getPriceUseCase;
    private final PriceRestMapper mapper;

    public PriceController(GetPriceUseCase getPriceUseCase, PriceRestMapper priceRestMapper) {
        this.getPriceUseCase = getPriceUseCase;
        this.mapper = priceRestMapper;
    }

    @PostMapping
    public ResponseEntity<PriceResponseDTO> getPrice(@Valid @RequestBody PriceRequestDTO request) {

        return getPriceUseCase.getPrice(request.productId(), request.brandId(), request.applicationDate()).map(mapper::toDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
