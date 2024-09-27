package com.kimdabang.kdbserver.review.review.dto.in;

import com.kimdabang.kdbserver.common.entity.SnowFlakeGenerator;
import com.kimdabang.kdbserver.review.media.domain.ReviewMedia;
import com.kimdabang.kdbserver.review.review.domain.Review;
import com.kimdabang.kdbserver.review.review.vo.in.ReviewRequestVo;
import com.kimdabang.kdbserver.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {
    private String productCode;
    private Long purchaseCode;
    private String options;
    private Integer rating;
    private String text;
    private String mediaType;
    private String mediaURL;

    public static ReviewRequestDto toRequestDto(ReviewRequestVo reviewRequestVo) {
        return ReviewRequestDto.builder()
                .productCode(reviewRequestVo.getProductCode())
                .purchaseCode(reviewRequestVo.getPurchaseCode())
                .options(reviewRequestVo.getOptions())
                .rating(reviewRequestVo.getRating())
                .text(reviewRequestVo.getText())
                .mediaType(reviewRequestVo.getMediaType())
                .mediaURL(reviewRequestVo.getMediaURL())
                .build();
    }
    public Review toReview(SnowFlakeGenerator snowFlakeGenerator, User user) {
        return Review.builder()
                .reviewCode(snowFlakeGenerator.generateUniqueId())
                .productCode(productCode)
                .purchaseCode(purchaseCode)
                .options(options)
                .creationDate(new Date())
                .rating(rating)
                .uuid(user.getUuid())
                .nickname(user.getNickname())
                .text(text)
                .build();
    }
    public ReviewMedia toReviewMedia(Review review) {
        return ReviewMedia.builder()
                .reviewCode(review.getReviewCode())
                .review(review)
                .mediaType(mediaType)
                .mediaURL(mediaURL)
                .build();
    }
}