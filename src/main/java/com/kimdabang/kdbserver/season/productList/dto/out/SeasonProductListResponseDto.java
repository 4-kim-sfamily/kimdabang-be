package com.kimdabang.kdbserver.season.productList.dto.out;

import com.kimdabang.kdbserver.season.productList.vo.SeasonProductListResponseVo;
import lombok.Builder;

public class SeasonProductListResponseDto {
    private Long id;
    private Long seasonId;
    private String productCode;

    public SeasonProductListResponseVo toResponseVo() {
        return SeasonProductListResponseVo.builder()
                .id(id)
                .seasonId(seasonId)
                .productCode(productCode)
                .build();
    }

    @Builder
    public SeasonProductListResponseDto(Long id, Long seasonId, String productCode) {
        this.id = id;
        this.seasonId = seasonId;
        this.productCode = productCode;
    }
}
