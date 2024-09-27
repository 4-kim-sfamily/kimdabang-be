package com.kimdabang.kdbserver.season.productList.vo;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SeasonProductListResponseVo {
    private Long id;
    private Long seasonId;
    private String productCode;
}
