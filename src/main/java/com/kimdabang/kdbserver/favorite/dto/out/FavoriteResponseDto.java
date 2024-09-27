package com.kimdabang.kdbserver.favorite.dto.out;

import com.kimdabang.kdbserver.favorite.vo.FavoriteResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FavoriteResponseDto {

    private String productCode;

    public FavoriteResponseVo toFavoriteResponseVo() {
        return FavoriteResponseVo.builder()
                .productCode(productCode)
                .build();
    }

    @Builder
    public FavoriteResponseDto(String productCode) {

        this.productCode = productCode;

    }
}
