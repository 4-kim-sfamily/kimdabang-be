package com.kimdabang.kdbserver.product.detail.infrastructure;

import com.kimdabang.kdbserver.product.detail.domain.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

    Optional<ProductDetail> findById(Long productId);
    Optional<ProductDetail> findByProductCode(String productCode);
}
