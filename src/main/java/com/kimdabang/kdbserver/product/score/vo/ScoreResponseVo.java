package com.kimdabang.kdbserver.product.score.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ScoreResponseVo {
    private Double rating;
    private Integer reviewCount;
    private Integer favoriteCount;
}
