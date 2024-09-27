package com.kimdabang.kdbserver.product.product.infrastructure;

import com.kimdabang.kdbserver.product.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long productId);
    Optional<Product> findByProductCode(String productCode);

    @Query(value = "SELECT * FROM product WHERE category_id = :categoryId ORDER BY id LIMIT :size OFFSET :offset", nativeQuery = true)
    List<Product> findProductsByCategoryWithPagination(
            @Param("categoryId") Long categoryId,
            @Param("offset") int offset,
            @Param("size") int size
    );

    @Query(value = "SELECT COUNT(p) FROM Product p WHERE p.categoryId = :categoryId")
    Long countProductsByCategory(@Param("categoryId") Long categoryId);

    boolean existsByProductCode(String productCode);
}
