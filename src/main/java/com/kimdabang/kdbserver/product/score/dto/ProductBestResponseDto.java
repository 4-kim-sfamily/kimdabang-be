package com.kimdabang.kdbserver.product.score.dto;

import com.kimdabang.kdbserver.product.score.domain.ProductScore;
import com.kimdabang.kdbserver.product.score.vo.ProductBestResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductBestResponseDto {
    private String productCode;
    private Long categoryId;

    public static ProductBestResponseDto toResponseDto(ProductScore productScore) {
        return ProductBestResponseDto.builder()
                .productCode(productScore.getProductCode())
                .categoryId(productScore.getCategoryId())
                .build();
    }

    public ProductBestResponseVo toResponseVo() {
        return ProductBestResponseVo.builder()
                .productCode(productCode)
                .build();
    }
}
