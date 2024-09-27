package com.kimdabang.kdbserver.product.detail.dto;

import com.kimdabang.kdbserver.product.detail.domain.ProductDetail;
import com.kimdabang.kdbserver.product.detail.vo.ProductDetailRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailRequestDto {
    private String productCode;
    private String content;

    public static ProductDetailRequestDto toRequestDto(ProductDetailRequestVo productDetailRequestVo) {
        return ProductDetailRequestDto.builder()
                .productCode(productDetailRequestVo.getProductCode())
                .content(productDetailRequestVo.getContent())
                .build();
    }
    public ProductDetail toEntity() {
        return ProductDetail.builder()
                .productCode(productCode)
                .content(content)
                .build();
    }
}
