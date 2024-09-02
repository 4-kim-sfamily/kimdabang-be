package com.kimdabang.kdbserver.product.media.application;

import com.kimdabang.kdbserver.product.media.dto.in.ProductMediaRequestDto;
import com.kimdabang.kdbserver.product.media.dto.out.ProductMediaResponseDto;

import java.util.List;

public interface ProductMediaService {

    void addProductMedia(ProductMediaRequestDto productMediaDto);
    void updateProductMedia(ProductMediaRequestDto productMediaDto);
    void deleteProductMedia(Long productMediaId);
//    ProductMediaResponseDto getProductMedia(Long productMediaId);
//    List<ProductMediaResponseDto> getAllProductMedia();
}
