package com.kimdabang.kdbserver.review.review.dto.in;

import com.kimdabang.kdbserver.review.media.domain.ReviewMedia;
import com.kimdabang.kdbserver.review.review.domain.Review;
import com.kimdabang.kdbserver.review.review.vo.in.ReviewUpdateRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewUpdateRequestDto {
    private Long reviewCode;
    private Integer rating;
    private String text;
    private String mediaType;
    private String mediaURL;

    public static ReviewUpdateRequestDto toRequestDto(ReviewUpdateRequestVo reviewUpdateRequestVo) {
        return ReviewUpdateRequestDto.builder()
                .reviewCode(reviewUpdateRequestVo.getReviewCode())
                .rating(reviewUpdateRequestVo.getRating())
                .text(reviewUpdateRequestVo.getText())
                .mediaType(reviewUpdateRequestVo.getMediaType())
                .mediaURL(reviewUpdateRequestVo.getMediaURL())
                .build();
    }
    public Review toReview(Review review) {
        return Review.builder()
                .id(review.getId())
                .reviewCode(review.getReviewCode())
                .productCode(review.getProductCode())
                .options(review.getOptions())
                .creationDate(new Date())
                .rating(rating)
                .text(text)
                .uuid(review.getUuid())
                .nickname(review.getNickname())
                .build();
    }
    public ReviewMedia toReviewMedia(ReviewMedia reviewMedia) {
        return ReviewMedia.builder()
                .id(reviewMedia.getId())
                .reviewCode(reviewMedia.getReviewCode())
                .review(reviewMedia.getReview())
                .mediaType(mediaType)
                .mediaURL(mediaURL)
                .build();
    }
}
