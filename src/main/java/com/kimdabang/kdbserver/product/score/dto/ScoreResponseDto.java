package com.kimdabang.kdbserver.product.score.dto;


import com.kimdabang.kdbserver.product.score.domain.ProductScore;
import com.kimdabang.kdbserver.product.score.vo.ScoreResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScoreResponseDto {

    private Long rating;
    private Integer reviewCount;
    private Integer favoriteCount;

    public static ScoreResponseDto toResponseDto(ProductScore productScore) {
        return ScoreResponseDto.builder()
                .rating(productScore.getRating())
                .reviewCount(productScore.getReviewCount())
                .favoriteCount(productScore.getFavoriteCount())
                .build();
    }

    public ScoreResponseVo toResponseVo() {
        return ScoreResponseVo.builder()
                .rating(rating)
                .reviewCount(reviewCount)
                .favoriteCount(favoriteCount)
                .build();
    }
}
