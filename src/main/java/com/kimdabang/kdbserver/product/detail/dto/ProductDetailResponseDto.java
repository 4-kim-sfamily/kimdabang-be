package com.kimdabang.kdbserver.product.detail.dto;

import com.kimdabang.kdbserver.product.detail.domain.ProductDetail;
import com.kimdabang.kdbserver.product.detail.vo.ProductDetailResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailResponseDto {

    private String productCode;
    private String content;

    public ProductDetailResponseVo toResponseVo() {
        return ProductDetailResponseVo.builder()
                .productCode(productCode)
                .content(content)
                .build();
    }
    public static ProductDetailResponseDto toResponseDto(ProductDetail productDetail) {
        return ProductDetailResponseDto.builder()
                .productCode(productDetail.getProductCode())
                .content(productDetail.getContent())
                .build();
    }
}