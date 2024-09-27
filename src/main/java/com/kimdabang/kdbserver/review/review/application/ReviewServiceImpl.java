package com.kimdabang.kdbserver.review.review.application;

import com.kimdabang.kdbserver.auth.infrastructure.AuthRepository;
import com.kimdabang.kdbserver.common.entity.SnowFlakeGenerator;
import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.common.jwt.JwtTokenProvider;
import com.kimdabang.kdbserver.review.media.domain.ReviewMedia;
import com.kimdabang.kdbserver.review.media.infrastructure.ReviewMediaRepository;
import com.kimdabang.kdbserver.review.review.domain.Review;
import com.kimdabang.kdbserver.review.review.dto.in.ReviewRequestDto;
import com.kimdabang.kdbserver.review.review.dto.in.ReviewUpdateRequestDto;
import com.kimdabang.kdbserver.review.review.dto.out.ReviewResponseDto;
import com.kimdabang.kdbserver.review.review.infrastructure.ReviewRepository;
import com.kimdabang.kdbserver.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final JwtTokenProvider jwtTokenProvider;
    private final ReviewRepository reviewRepository;
    private final AuthRepository authRepository;
    private final ReviewMediaRepository reviewMediaRepository;
    private final SnowFlakeGenerator snowFlakeGenerator;

    @Transactional
    @Override
    public void addReview(ReviewRequestDto reviewRequestDto, String authorization) {
        try {
            String uuid = jwtTokenProvider.useToken(authorization);
            User user = authRepository.findByUuid(uuid).orElseThrow(
                    () -> new CustomException(USER_NOT_FOUND)
            );
            Review review = reviewRepository.save(reviewRequestDto.toReview(snowFlakeGenerator, user));
            reviewMediaRepository.save(reviewRequestDto.toReviewMedia(review));
        } catch (Exception e) {
            // 로그를 남기고 예외 처리
            throw new CustomException(REVIWE_PROCESS_FAILED);
        }
    }

    @Transactional
    @Override
    public void putReview(ReviewUpdateRequestDto reviewUpdateRequestDto, String authorization) {
        try {
            String uuid = jwtTokenProvider.useToken(authorization);
            Review review = reviewRepository.findByReviewCodeAndUuid(reviewUpdateRequestDto.getReviewCode(), uuid)
                    .orElseThrow(
                            () -> new CustomException(REVIWE_NOT_FOUND)
                    );
            ReviewMedia reviewMedia = reviewMediaRepository.findByReviewCode(reviewUpdateRequestDto.getReviewCode())
                            .orElseThrow(
                                    () -> new CustomException(REVIWE_NOT_FOUND)
                            );
            reviewRepository.save(reviewUpdateRequestDto.toReview(review));
            reviewMediaRepository.save(reviewUpdateRequestDto.toReviewMedia(reviewMedia));
        } catch (Exception e) {
            // 로그를 남기고 예외 처리
            throw new CustomException(REVIWE_PROCESS_FAILED);
        }
    }
    @Transactional
    @Override
    public void deleteReview(Long reviewCode, String authorization) {
        try {
            String uuid = jwtTokenProvider.useToken(authorization);
            Review review = reviewRepository.findByReviewCodeAndUuid(reviewCode, uuid)
                    .orElseThrow(
                            () -> new CustomException(REVIWE_NOT_FOUND)
                    );
            ReviewMedia reviewMedia = reviewMediaRepository.findByReviewCode(reviewCode)
                    .orElseThrow(
                            () -> new CustomException(REVIWE_NOT_FOUND)
                    );
            reviewRepository.delete(review);
            reviewMediaRepository.delete(reviewMedia);
        } catch (Exception e) {
            // 로그를 남기고 예외 처리
            throw new CustomException(REVIWE_PROCESS_FAILED);
        }
    }

    @Override
    public List<ReviewResponseDto> getUserReviewList(Date start, Date end, String authorization) {
        String uuid = jwtTokenProvider.useToken(authorization);
        List<Review> reviewList = reviewRepository.findByUuidAndCreationDateBetween(uuid, start, end);
        if(!reviewList.isEmpty()) {
            return reviewList.stream().map(
                    ReviewResponseDto::toResponseDto
            ).toList();
        }
        else {
            throw new CustomException(REVIWE_NOT_FOUND);
        }
    }
    @Override
    public List<ReviewResponseDto> getReviewList(String productCode, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        List<Review> reviewList = reviewRepository.findByProductCode(productCode, pageable);
        if(!reviewList.isEmpty()) {
            return reviewList.stream().map(
                    ReviewResponseDto::toResponseDto
            ).toList();
        }
        else {
            throw new CustomException(REVIWE_NOT_FOUND);
        }
    }
    @Override
    public ReviewResponseDto getReview(Long reviewCode) {
        Review review = reviewRepository.findByReviewCode(reviewCode)
                .orElseThrow(
                        ()->new CustomException(REVIWE_NOT_FOUND)
                );
            return ReviewResponseDto.toResponseDto(review);
    }
}
