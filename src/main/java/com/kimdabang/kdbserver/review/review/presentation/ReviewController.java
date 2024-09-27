package com.kimdabang.kdbserver.review.review.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.review.review.application.ReviewService;
import com.kimdabang.kdbserver.review.review.dto.in.ReviewRequestDto;
import com.kimdabang.kdbserver.review.review.dto.in.ReviewUpdateRequestDto;
import com.kimdabang.kdbserver.review.review.dto.out.ReviewResponseDto;
import com.kimdabang.kdbserver.review.review.vo.in.ReviewRequestVo;
import com.kimdabang.kdbserver.review.review.vo.in.ReviewUpdateRequestVo;
import com.kimdabang.kdbserver.review.review.vo.out.ReviewResponseVo;
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
    @PostMapping("/put-review")
    public CommonResponseEntity<Void> putReview(
            @RequestHeader("Authorization") String authorization,
            @RequestBody ReviewUpdateRequestVo reviewUpdateRequestVo) throws ParseException {

        reviewService.putReview(ReviewUpdateRequestDto.toRequestDto(reviewUpdateRequestVo), authorization);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "리뷰 갱신 성공",
                null
        );
    }
    @DeleteMapping("/delete-review")
    public CommonResponseEntity<Void> deleteReview(
            @RequestHeader("Authorization") String authorization,
            @RequestParam(value = "reviewCode") Long reviewCode) throws ParseException {

        reviewService.deleteReview(reviewCode, authorization);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "리뷰 삭제 성공",
                null
        );
    }

    @GetMapping("/get-myreview")
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
    @GetMapping("/get-reviewlist")
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
    @GetMapping("/get-review")
    public CommonResponseEntity<ReviewResponseVo> getReview(
            @RequestParam(value = "reviewCode") Long reviewCode) throws ParseException {
        ReviewResponseDto reviewResponseDto = reviewService.getReview(reviewCode);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "상품 리뷰 리스트 조회 성공",
                reviewResponseDto.toResponseVo()
        );
    }
    @GetMapping("/check-reviewavailable")
    public CommonResponseEntity<Boolean> checkReview(
            @RequestHeader("Authorization") String authorization,
            @RequestParam(value = "productCode") Long purchaseCode) throws ParseException {
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "상품 리뷰 리스트 조회 성공",
                reviewService.checkReview(purchaseCode, authorization)
        );
    }
}
