package com.kimdabang.kdbserver.review.review.application;

import com.kimdabang.kdbserver.common.dto.PageResponseDto;
import com.kimdabang.kdbserver.review.review.dto.in.ReviewRequestDto;
import com.kimdabang.kdbserver.review.review.dto.in.ReviewUpdateRequestDto;
import com.kimdabang.kdbserver.review.review.dto.out.ReviewResponseDto;
import com.kimdabang.kdbserver.review.review.vo.out.ReviewResponseVo;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ReviewService {
    void addReview(ReviewRequestDto reviewRequestDto, String authorization);
    void putReview(ReviewUpdateRequestDto reviewUpdateRequestDto, String authorization);
    List<ReviewResponseDto> getUserReviewList(Date startDate, Date endDate, String authorization);
    PageResponseDto getReviewList(String productCode, int page, int size);
    void deleteReview(Long reviewCode, String authorization);
    ReviewResponseDto getReview(Long reviewCode);
    Boolean checkReview(Long purchaseCode, String uuid);
    PageResponseDto searchReviewTest(String keyword, int page, int size);
}
