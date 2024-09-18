package com.kimdabang.kdbserver.product.media.infrastructure;

import com.kimdabang.kdbserver.product.media.domain.ProductMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductMediaRepository extends JpaRepository<ProductMedia, Long> {

    Optional<ProductMedia> findById(Long productMediaId);
    Optional<ProductMedia> findByProductId(Long productId);
    List<ProductMedia> findAllByProductCode(String productCode);

}
