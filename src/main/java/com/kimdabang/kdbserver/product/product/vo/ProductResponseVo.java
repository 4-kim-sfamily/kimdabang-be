package com.kimdabang.kdbserver.product.product.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseVo {

    private String productCode;
    private String productName;
    private Long productPrice;
    private String description;
    private Date releaseDate;
    private String content;
    private Long categoryId;

}
