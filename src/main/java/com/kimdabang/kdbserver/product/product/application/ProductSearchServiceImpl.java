package com.kimdabang.kdbserver.product.product.application;

import com.kimdabang.kdbserver.product.product.domain.Product;
import com.kimdabang.kdbserver.product.product.domain.ProductDocument;
import com.kimdabang.kdbserver.product.product.domain.ProductMapper;
import com.kimdabang.kdbserver.product.product.domain.ProductPartialDocument;
import com.kimdabang.kdbserver.product.product.infrastructure.ProductRepository;
import com.kimdabang.kdbserver.product.product.infrastructure.ProductSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductSearchServiceImpl implements ProductSearchService{

    private final ProductRepository productRepository;
    private final ProductSearchRepository productSearchRepository;

    @Override
    public void indexProducts() {

        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            ProductDocument document = ProductMapper.toDocument(product);
            productSearchRepository.save(document);
        }

    }
    
    public List<ProductPartialDocument> searchProducts(String keyword, Pageable pageable) {

        List<ProductDocument> searchResults = productSearchRepository.findByProductNameContainingOrDescriptionContaining(keyword, keyword, pageable);

        return searchResults.stream()
                .map(product -> ProductPartialDocument.builder()
                        .productCode(product.getProductCode())
                        .productName(product.getProductName())
                        .categoryId(product.getCategoryId())
                        .build())
                .collect(Collectors.toList());
    }

}
