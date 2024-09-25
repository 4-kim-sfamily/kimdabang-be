package com.kimdabang.kdbserver.reviwe.reviwe.application;

import com.kimdabang.kdbserver.reviwe.reviwe.dto.in.ReviewRequestDto;
import com.kimdabang.kdbserver.reviwe.reviwe.dto.out.ReviewResponseDto;

import java.util.Date;
import java.util.List;

public interface ReviewService {
    void addReview(ReviewRequestDto reviewRequestDto, String authorization);
    List<ReviewResponseDto> getUserReviewList(Date startDate, Date endDate, String authorization);
    List<ReviewResponseDto> getReviewList(String productCode, int page, int size);
}
