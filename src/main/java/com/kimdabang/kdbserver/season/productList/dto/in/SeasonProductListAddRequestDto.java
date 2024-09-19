package com.kimdabang.kdbserver.season.productList.dto.in;

import com.kimdabang.kdbserver.season.productList.domain.SeasonProductList;
import com.kimdabang.kdbserver.season.season.domain.Season;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SeasonProductListAddRequestDto {

    private Long seasonId;
    private String productCode;

    public SeasonProductList toEntity(Season season) {
        return SeasonProductList.builder()
                .season(season)
                .productCode(productCode)
                .build();
    }

    @Builder
    public SeasonProductListAddRequestDto(Long seasonId, String productCode) {
        this.seasonId = seasonId;
        this.productCode = productCode;
    }
}
