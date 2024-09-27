package com.kimdabang.kdbserver.product.optionDetail.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.product.optionDetail.application.OptionDetailService;
import com.kimdabang.kdbserver.product.optionDetail.vo.OptionDetailResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/optiondetail")
public class OptionDetailController {

    private final OptionDetailService optionDetailService;

    @GetMapping("/{productCode}")
    public CommonResponseEntity<OptionDetailResponseVo> getOptionDetail(
            @PathVariable("productCode") String productCode,
            @RequestParam Long optionId) {
        OptionDetailResponseVo optionDetailResponseVo = optionDetailService.getOptionDetail(productCode, optionId);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "개별 옵션 세부정보 조회 성공",
                optionDetailResponseVo
        );
    }

}