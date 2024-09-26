package com.kimdabang.kdbserver.review.review.application;

import com.kimdabang.kdbserver.review.review.dto.in.ReviewRequestDto;
import com.kimdabang.kdbserver.review.review.dto.in.ReviewUpdateRequestDto;
import com.kimdabang.kdbserver.review.review.dto.out.ReviewResponseDto;

import java.util.Date;
import java.util.List;

public interface ReviewService {
    void addReview(ReviewRequestDto reviewRequestDto, String authorization);
    void putReview(ReviewUpdateRequestDto reviewUpdateRequestDto, String authorization);
    List<ReviewResponseDto> getUserReviewList(Date startDate, Date endDate, String authorization);
    List<ReviewResponseDto> getReviewList(String productCode, int page, int size);
    void deleteReview(Long reviewCode, String authorization);
}
