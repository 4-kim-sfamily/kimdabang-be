package com.kimdabang.kdbserver.product.detail.application;

import com.kimdabang.kdbserver.product.detail.dto.ProductDetailRequestDto;
import com.kimdabang.kdbserver.product.detail.dto.ProductDetailResponseDto;

public interface ProductDetailService {
    ProductDetailResponseDto getProductDetail(String productCode);
    void addProductDetail(ProductDetailRequestDto productDetailRequestDto);
    void putProductDetail(ProductDetailRequestDto productDetailRequestDto);
}
