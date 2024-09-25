package com.kimdabang.kdbserver.product.product.application;


import com.kimdabang.kdbserver.product.product.domain.ProductDocument;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductSearchService {

    public void indexProducts();
    public List<ProductDocument> searchProducts(String keyword, Pageable pageable);

}
