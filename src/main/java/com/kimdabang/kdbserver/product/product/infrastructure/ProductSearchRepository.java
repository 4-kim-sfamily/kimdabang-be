package com.kimdabang.kdbserver.product.product.infrastructure;

import com.kimdabang.kdbserver.product.product.domain.ProductDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductSearchRepository extends ElasticsearchRepository<ProductDocument, String> {

    List<ProductDocument> findByProductNameContainingOrDescriptionContaining(String name, String description, Pageable pageable);
}
