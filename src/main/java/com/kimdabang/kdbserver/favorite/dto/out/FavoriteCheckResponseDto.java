package com.kimdabang.kdbserver.favorite.dto.out;

import com.kimdabang.kdbserver.favorite.vo.FavoriteCheckResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FavoriteCheckResponseDto {

    private boolean isFavorite;

    public FavoriteCheckResponseVo toVo() {
        return FavoriteCheckResponseVo.builder()
                .isFavorite(isFavorite)
                .build();
    }

    @Builder
    public FavoriteCheckResponseDto(Boolean isFavorite) {

        this.isFavorite = isFavorite;

    }
}
