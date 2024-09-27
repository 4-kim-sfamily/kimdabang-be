package com.kimdabang.kdbserver.review.review.dto.out;

import com.kimdabang.kdbserver.review.review.domain.Review;
import com.kimdabang.kdbserver.review.review.vo.out.ReviewResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {

    private Long reviewCode;
    private String productCode;
    private String options;
    private String nickName;
    private Date creationDate;
    private Integer rating;
    private String text;

    public static ReviewResponseDto toResponseDto (Review review) {
        return ReviewResponseDto.builder()
                .reviewCode(review.getReviewCode())
                .productCode(review.getProductCode())
                .nickName(review.getNickname())
                .options(review.getOptions())
                .creationDate(review.getCreationDate())
                .rating(review.getRating())
                .text(review.getText())
                .build();
    }
    public ReviewResponseVo toResponseVo() {
        return ReviewResponseVo.builder()
                .reviewCode(reviewCode)
                .productCode(productCode)
                .nickName(nickName)
                .options(options)
                .creationDate(creationDate)
                .rating(rating)
                .text(text)
                .build();
    }
}
