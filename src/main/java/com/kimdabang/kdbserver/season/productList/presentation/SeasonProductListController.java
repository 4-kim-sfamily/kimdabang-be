package com.kimdabang.kdbserver.season.productList.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.season.mediaList.vo.SeasonMediaListUpdateRequestVo;
import com.kimdabang.kdbserver.season.productList.application.SeasonProductListService;
import com.kimdabang.kdbserver.season.productList.dto.in.SeasonProductListAddRequestDto;
import com.kimdabang.kdbserver.season.productList.dto.in.SeasonProductListUpdateRequestDto;
import com.kimdabang.kdbserver.season.productList.vo.SeasonProductListAddRequestVo;
import com.kimdabang.kdbserver.season.productList.vo.SeasonProductListUpdateRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
                "시즌상품리스트 등록 성공",
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
                "시즌상품리스트 업데이트 성공",
                null
        );
    }

    @DeleteMapping
    public CommonResponseEntity<Void> deleteSeasonProductList(@RequestParam Long id) {
        log.info("seasonProductListId = {}" , id);
        seasonProductListService.deleteSeasonProductList(id);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "시즌상품리스트 삭제 성공",
                null
        );
    }
}
