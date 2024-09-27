package com.kimdabang.kdbserver.season.productList.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.season.mediaList.vo.SeasonMediaListUpdateRequestVo;
import com.kimdabang.kdbserver.season.productList.application.SeasonProductListService;
import com.kimdabang.kdbserver.season.productList.dto.in.SeasonProductListAddRequestDto;
import com.kimdabang.kdbserver.season.productList.dto.in.SeasonProductListUpdateRequestDto;
import com.kimdabang.kdbserver.season.productList.dto.out.SeasonProductListResponseDto;
import com.kimdabang.kdbserver.season.productList.vo.SeasonProductListAddRequestVo;
import com.kimdabang.kdbserver.season.productList.vo.SeasonProductListResponseVo;
import com.kimdabang.kdbserver.season.productList.vo.SeasonProductListUpdateRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/season-product-list")
public class SeasonProductListController {

    private final SeasonProductListService seasonProductListService;

    @PostMapping
    public CommonResponseEntity<Void> createSeasonProductList(@RequestBody SeasonProductListAddRequestVo seasonProductListAddRequestVo) {
        log.info("seasonProductListAddRequestVo = {}", seasonProductListAddRequestVo);
        SeasonProductListAddRequestDto seasonProductListAddRequestDto = SeasonProductListAddRequestDto.builder()
                .seasonId(seasonProductListAddRequestVo.getSeasonId())
                .productCode(seasonProductListAddRequestVo.getProductCode())
                .build();
        seasonProductListService.addSeasonProductList(seasonProductListAddRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "시즌 상품 리스트 등록 성공",
                null
        );
    }

    @PutMapping
    public CommonResponseEntity<Void> updateSeasonProductList(@RequestBody SeasonProductListUpdateRequestVo seasonProductListUpdateRequestVo) {
        log.info("seasonProductListUpdateRequestVo ={}", seasonProductListUpdateRequestVo);
        SeasonProductListUpdateRequestDto seasonProductListUpdateRequestDto = SeasonProductListUpdateRequestDto.builder()
                .id(seasonProductListUpdateRequestVo.getId())
                .seasonId(seasonProductListUpdateRequestVo.getSeasonId())
                .productCode(seasonProductListUpdateRequestVo.getProductCode())
                .build();
        seasonProductListService.updateSeasonProductList(seasonProductListUpdateRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "시즌 상품 리스트 업데이트 성공",
                null
        );
    }

    @DeleteMapping
    public CommonResponseEntity<Void> deleteSeasonProductList(@RequestParam Long id) {
        log.info("seasonProductListId = {}" , id);
        seasonProductListService.deleteSeasonProductList(id);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "시즌 상품 리스트 삭제 성공",
                null
        );
    }

    @GetMapping
    public CommonResponseEntity<List<SeasonProductListResponseVo>> getAllSeasonProductList() {
        List<SeasonProductListResponseDto> seasonProductListResponseDtos = seasonProductListService.getAllSeasonProductList();
        List<SeasonProductListResponseVo> seasonProductListResponseVos = seasonProductListResponseDtos.stream()
                .map(SeasonProductListResponseDto::toResponseVo)
                .toList();
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "시즌 상품 리스트 전체 조회 성공",
                seasonProductListResponseVos
        );
    }

    @GetMapping("/{id}")
    public CommonResponseEntity<SeasonProductListResponseVo> getOneSeasonProductList(@PathVariable Long id) {
        SeasonProductListResponseDto seasonProductListResponseDto = seasonProductListService.getOneSeasonProductList(id);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "시즌 상품 리스트 조회 성공",
                seasonProductListResponseDto.toResponseVo()
        );
    }

    @GetMapping("/bySeason/{seasonId}")
    public CommonResponseEntity<List<String>> getAllProductCodeBySeasonId(@PathVariable Long seasonId) {
        List<String> productCodesBySeasonId = seasonProductListService.getAllProductCodeBySeasonId(seasonId);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "해당 시즌 상품 코드 전체 조회 성공",
                productCodesBySeasonId
        );
    }
}
