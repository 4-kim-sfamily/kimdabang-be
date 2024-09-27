package com.kimdabang.kdbserver.favorite.dto.in;

import com.kimdabang.kdbserver.favorite.domain.Favorite;
import com.kimdabang.kdbserver.product.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FavoriteRequestDto {

    private String productCode;
    private String accessToken;

    public Favorite toFavoriteEntity(String userUuid, Boolean isCanceled) {
        return Favorite.builder()
                .productCode(productCode)
                .userUuid(userUuid)
                .isCanceled(isCanceled)
                .build();
    }

    @Builder
    public FavoriteRequestDto(String productCode, String accessToken) {

        this.productCode = productCode;
        this.accessToken = accessToken;

    }

}
