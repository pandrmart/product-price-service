package com.example.productprice.infrajpa.adapter.out;

import com.example.productprice.domain.entity.ProductPrice;
import com.example.productprice.domain.port.out.GetProductPricePort;
import com.example.productprice.infrajpa.mapper.ProductPriceMapper;
import com.example.productprice.infrajpa.repository.ProductPriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Adapter for the ProductPrice persistence port.
 * <p>
 * This class connects the domain layer to the persistence infrastructure (JPA).
 * It translates domain-level requests into database queries and converts JPA entities
 * back into domain objects. It also handles technical exceptions from the database.
 */
@Repository
@Slf4j
@RequiredArgsConstructor
public class GetProductProductPriceAdapter implements GetProductPricePort {

    private final ProductPriceRepository productPriceRepository;
    private final ProductPriceMapper mapper;

    @Override
    public Optional<ProductPrice> getProductPrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        log.debug("Searching in database with productId={}, brandId={}, applicationDate={}",
                productId, brandId, applicationDate);
        try {
            return productPriceRepository.findPrice(productId, brandId, applicationDate)
                    .stream()
                    .findFirst()
                    .map(mapper::toDomain);
        } catch (DataAccessException ex) {
            log.error("An error occurred during database search: {}", ex.getMessage(), ex);
            throw new RuntimeException("Failed to retrieve data from database", ex);
        }
    }
}
