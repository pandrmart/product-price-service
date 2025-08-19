package com.example.infrajpa.adapter.out;

import com.example.domain.entity.Price;
import com.example.domain.port.out.GetPricePort;
import com.example.infrajpa.mapper.PriceMapper;
import com.example.infrajpa.repository.PriceRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class GetPriceAdapter implements GetPricePort {

    private final PriceRepository priceRepository;
    private final PriceMapper mapper;

    public GetPriceAdapter(PriceRepository priceRepository, PriceMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.mapper = priceMapper;
    }

    @Override
    public Optional<Price> getPrice(Long productId, Long brandId, LocalDateTime applicationDate) {
//        return priceRepository.findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(productId, brandId, applicationDate, applicationDate).map(mapper::toDomain);
        return priceRepository.findPrice(productId, brandId, applicationDate).stream().findFirst().map(mapper::toDomain);
    }
}
