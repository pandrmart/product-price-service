package com.example.productprice.infrajpa.repository;

import com.example.productprice.infrajpa.entity.ProductPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductPriceRepository extends JpaRepository<ProductPriceEntity, Long> {

    @Query("""
                SELECT p
                FROM ProductPriceEntity p
                WHERE p.productId = :productId
                    AND p.brandId = :brandId
                    AND :applicationDate between p.startDate AND p.endDate
                ORDER BY p.priority DESC
            """)
    List<ProductPriceEntity> findPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
