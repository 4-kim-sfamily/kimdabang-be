package com.kimdabang.kdbserver.product.detail.application;


import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.product.detail.domain.ProductDetail;
import com.kimdabang.kdbserver.product.detail.dto.ProductDetailRequestDto;
import com.kimdabang.kdbserver.product.detail.dto.ProductDetailResponseDto;
import com.kimdabang.kdbserver.product.detail.infrastructure.ProductDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {

    private final ProductDetailRepository productDetailRepository;

    @Override
    public ProductDetailResponseDto getProductDetail(String productCode) {
        ProductDetail productDetail = productDetailRepository.findByProductCode(productCode)
                .orElseThrow(() -> new CustomException(PRODUCTDETAIL_NOT_FOUND));
        return ProductDetailResponseDto.toResponseDto(productDetail);
    }
    @Override
    public void addProductDetail(ProductDetailRequestDto productDetailRequestDto) {
        Optional<ProductDetail> productDetail = productDetailRepository.findByProductCode(productDetailRequestDto.getProductCode());

        if(productDetail.isPresent()){
            throw new CustomException(PRODUCTDETAIL_ALREADY_INIT);
        }
        else {
            productDetailRepository.save(productDetailRequestDto.toEntity());
        }

    }
    @Override
    public void putProductDetail(ProductDetailRequestDto productDetailRequestDto) {
        ProductDetail productDetail = productDetailRepository.findByProductCode(productDetailRequestDto.getProductCode())
                .orElseThrow(() -> new CustomException(PRODUCTDETAIL_NOT_FOUND));
        productDetail.updateContent(productDetailRequestDto.getContent());
        productDetailRepository.save(productDetail);

    }

}
