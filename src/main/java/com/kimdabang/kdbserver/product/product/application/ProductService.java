package com.kimdabang.kdbserver.product.product.application;

import com.kimdabang.kdbserver.product.product.dto.in.ProductRequestDto;
import com.kimdabang.kdbserver.product.product.dto.out.ProductResponseDto;
import com.kimdabang.kdbserver.product.product.vo.ProductResponseVo;

import java.util.List;

public interface ProductService {

    void addProduct(ProductRequestDto productDto);
    void updateProduct(ProductRequestDto productDto);
    void deleteProduct(String productCode);
    ProductResponseDto getProduct(String productCode);
    List<ProductResponseVo> getAllProducts();

    List<ProductResponseVo> getProductsByCategory(Long categoryId, int page, int size);
    long getTotalPagesByCategory(Long categoryId, int size);

    // elasticSearch
    public void indexProducts();

}
