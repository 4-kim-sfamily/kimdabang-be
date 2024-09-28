package com.kimdabang.kdbserver.product.score.presentation;

import com.kimdabang.kdbserver.common.dto.PageResponseDto;
import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.common.vo.PageResponseVo;
import com.kimdabang.kdbserver.product.score.application.ProductScoreService;
import com.kimdabang.kdbserver.product.score.dto.ScoreResponseDto;
import com.kimdabang.kdbserver.product.score.vo.ScoreResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/score")
public class ProductScoreController {

    private final ProductScoreService productScoreService;

    @Operation(summary = "CategoryBestGet API", description = "CategoryBestGet API 입니다.")
    @GetMapping("/get-categorybest")
    public CommonResponseEntity<PageResponseVo> getCategoryBestList(
            @RequestParam(value = "categoryId") Long categoryId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
            ) throws ParseException {

        PageResponseDto pageResponseDto =
                productScoreService.getCategoryBestList(categoryId, page, size);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "CategoryBest 조회 성공",
                pageResponseDto.toResponseVo()
        );
    }
    @Operation(summary = "BestGet API", description = "BestGet API 입니다.")
    @GetMapping("/get-best")
    public CommonResponseEntity<PageResponseVo> getBestList(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) throws ParseException {

        PageResponseDto pageResponseDto = productScoreService.getBestList(page, size);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "Best 조회 성공",
                pageResponseDto.toResponseVo()
        );
    }

    @Operation(summary = "RecommendationGet API", description = "BRecommendationGet API 입니다.")
    @GetMapping("/get-recommendation")
    public CommonResponseEntity<PageResponseVo> getRecommendationList(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ) throws ParseException {
        PageResponseDto pageResponseDto = productScoreService.getRecommendationList(page, size);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "추천 상품 조회 성공",
                pageResponseDto.toResponseVo()
        );
    }
    @Operation(summary = "ScoreGet API", description = "ScoreGet API 입니다.")
    @GetMapping("/get-score")
    public CommonResponseEntity<ScoreResponseVo> getScore(
            @RequestParam(value = "productCode") String productCode
    ) throws ParseException {
        ScoreResponseDto scoreResponseDto = productScoreService.getScore(productCode);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "상품 지표 조회 성공", scoreResponseDto.toResponseVo()
        );
    }
}
