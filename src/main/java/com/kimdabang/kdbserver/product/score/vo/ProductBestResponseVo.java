package com.kimdabang.kdbserver.product.score.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ProductBestResponseVo {
    private String productCode;
    private Long categoryId;
}
