package com.kimdabang.kdbserver.reviwe.reviwe.application;

import com.kimdabang.kdbserver.auth.infrastructure.AuthRepository;
import com.kimdabang.kdbserver.common.entity.SnowFlakeGenerator;
import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.common.jwt.JwtTokenProvider;
import com.kimdabang.kdbserver.reviwe.media.infrastructure.ReviewMediaRepository;
import com.kimdabang.kdbserver.reviwe.reviwe.domain.Review;
import com.kimdabang.kdbserver.reviwe.reviwe.dto.in.ReviewRequestDto;
import com.kimdabang.kdbserver.reviwe.reviwe.dto.out.ReviewResponseDto;
import com.kimdabang.kdbserver.reviwe.reviwe.infrastructure.ReviewRepository;
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
            Review review = reviewRequestDto.toReview(snowFlakeGenerator, user);
            reviewMediaRepository.save(reviewRequestDto.toReviewMedia(review));
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
}
