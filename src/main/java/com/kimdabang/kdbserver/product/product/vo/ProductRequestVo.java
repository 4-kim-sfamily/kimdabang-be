package com.kimdabang.kdbserver.product.product.vo;

import lombok.Getter;

import java.util.Date;

@Getter
public class ProductRequestVo {

    private String productCode;
    private String productName;
    private Long productPrice;
    private String description;
    private Date releaseDate;
    private String content;
    private Long categoryId;

}
