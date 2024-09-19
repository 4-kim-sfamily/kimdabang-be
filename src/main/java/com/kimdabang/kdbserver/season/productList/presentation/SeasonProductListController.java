package com.kimdabang.kdbserver.season.productList.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.season.productList.application.SeasonProductListService;
import com.kimdabang.kdbserver.season.productList.dto.in.SeasonProductListAddRequestDto;
import com.kimdabang.kdbserver.season.productList.vo.SeasonProductListAddRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
