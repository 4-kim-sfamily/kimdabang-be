package com.kimdabang.kdbserver.favorite.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteCheckResponseVo {

    private boolean isFavorite;

}
