package com.kimdabang.kdbserver.season.productList.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SeasonProductListAddRequestVo {
    private Long seasonId;
    private String productCode;
}
