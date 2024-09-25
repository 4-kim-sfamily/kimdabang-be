package com.kimdabang.kdbserver.reviwe.reviwe.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.reviwe.reviwe.application.ReviewService;
import com.kimdabang.kdbserver.reviwe.reviwe.dto.in.ReviewRequestDto;
import com.kimdabang.kdbserver.reviwe.reviwe.dto.out.ReviewResponseDto;
import com.kimdabang.kdbserver.reviwe.reviwe.vo.in.ReviewRequestVo;
import com.kimdabang.kdbserver.reviwe.reviwe.vo.out.ReviewResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/add-review")
    public CommonResponseEntity<Void> addReview(
            @RequestHeader("Authorization") String authorization,
            @RequestBody ReviewRequestVo reviewRequestVo) throws ParseException {

        reviewService.addReview(ReviewRequestDto.toRequestDto(reviewRequestVo), authorization);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "리뷰 등록 성공",
                null
        );
    }

    @GetMapping("/get-myreviwe")
    public CommonResponseEntity<List<ReviewResponseVo>> getUserReviewList(
            @RequestHeader("Authorization") String authorization,
            @RequestParam(value = "start") String start, @RequestParam(value = "end") String end) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
        start = start+"-00:00:00";
        end = end+"-23:59:59";
        Date startDate = formatter.parse(start);
        Date endDate = formatter.parse(end);
        List<ReviewResponseDto> reviewResponseDtoList = reviewService.getUserReviewList(startDate, endDate, authorization);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "내 리뷰 리스트 조회 성공",
                reviewResponseDtoList.stream()
                        .map(ReviewResponseDto::toResponseVo)
                        .toList()
        );
    }
    @GetMapping("/get-reviwelist")
    public CommonResponseEntity<List<ReviewResponseVo>> getReviewList(
            @RequestParam(value = "productCode") String productCode,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) throws ParseException {
        List<ReviewResponseDto> reviewResponseDtoList = reviewService.getReviewList(productCode, page, size);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "상품 리뷰 리스트 조회 성공",
                reviewResponseDtoList.stream()
                        .map(ReviewResponseDto::toResponseVo)
                        .toList()
        );
    }
}
