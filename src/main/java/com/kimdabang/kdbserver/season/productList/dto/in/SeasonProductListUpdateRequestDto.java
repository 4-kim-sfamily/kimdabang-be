package com.kimdabang.kdbserver.season.productList.dto.in;

import com.kimdabang.kdbserver.season.mediaList.dto.in.SeasonMediaListUpdateRequestDto;
import com.kimdabang.kdbserver.season.productList.domain.SeasonProductList;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SeasonProductListUpdateRequestDto {

    private Long id;
    private Long seasonId;
    private String productCode;

    public SeasonProductList toEntity(SeasonProductList seasonProductList) {
        return SeasonProductList.builder()
                .id(id)
                .season(seasonProductList.getSeason())
                .productCode(productCode)
                .build();
    }

    @Builder
    public SeasonProductListUpdateRequestDto(Long id, Long seasonId, String productCode) {
        this.id = id;
        this.seasonId = seasonId;
        this.productCode = productCode;
    }
}
