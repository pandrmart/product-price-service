package com.example.infrajpa.repository;

import com.example.infrajpa.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    Optional<PriceEntity> findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(Long productId, Long brandId, LocalDateTime applicationDate1, LocalDateTime applicationDate2);

    @Query("""
        SELECT p
        FROM PriceEntity p
        WHERE p.productId = :productId
            AND p.brandId = :brandId
            AND :applicationDate between p.startDate AND p.endDate
        ORDER BY p.priority DESC
    """)
    List<PriceEntity> findPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
