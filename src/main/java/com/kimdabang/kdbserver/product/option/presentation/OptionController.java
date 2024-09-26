package com.kimdabang.kdbserver.product.option.presentation;


import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.product.option.application.OptionService;
import com.kimdabang.kdbserver.product.option.dto.out.OptionResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/options")
public class OptionController {

    private final OptionService optionService;

    @GetMapping("/{productCode}")
    public CommonResponseEntity<List<OptionResponseDto>> getAllOptions(
            @PathVariable String productCode) {
        List<OptionResponseDto> optionResponseDtoList = optionService.getOptionListByProductCode(productCode, null);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "특정 상품 전체 옵션 조회",
                optionResponseDtoList
        );
    }

    @GetMapping("/{productCode}/family")
    public CommonResponseEntity<String> getParentOption(
            @PathVariable String productCode,
            @RequestParam Long optionId
    ) {
        String result = optionService.getFamilyOption(productCode, optionId);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "상위 옵션 조회 성공",
                result
        );
    }
}
