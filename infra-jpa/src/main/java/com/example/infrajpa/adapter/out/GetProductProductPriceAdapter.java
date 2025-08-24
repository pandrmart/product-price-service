package com.example.infrajpa.adapter.out;

import com.example.domain.entity.ProductPrice;
import com.example.domain.port.out.GetProductPricePort;
import com.example.infrajpa.mapper.ProductPriceMapper;
import com.example.infrajpa.repository.ProductPriceRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class GetProductProductPriceAdapter implements GetProductPricePort {

    private final ProductPriceRepository productPriceRepository;
    private final ProductPriceMapper mapper;

    public GetProductProductPriceAdapter(ProductPriceRepository productPriceRepository, ProductPriceMapper productPriceMapper) {
        this.productPriceRepository = productPriceRepository;
        this.mapper = productPriceMapper;
    }

    @Override
    public Optional<ProductPrice> getProductPrice(Long productId, Long brandId, LocalDateTime applicationDate) {
//        return priceRepository.findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(productId, brandId, applicationDate, applicationDate).map(mapper::toDomain);
        return productPriceRepository.findPrice(productId, brandId, applicationDate).stream().findFirst().map(mapper::toDomain);
    }
}
