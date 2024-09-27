package com.kimdabang.kdbserver.product.score.infrastructure;

import com.kimdabang.kdbserver.product.score.domain.ProductScore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductScoreRepository extends JpaRepository<ProductScore, Long> {
    Optional<ProductScore> findById(Long id);
    Optional<ProductScore> findByProductCode(String productCode);
    Page<ProductScore> findByCategoryId(Long categoryId, Pageable pageable);
    Page<ProductScore> findAll(Pageable pageable);

}
