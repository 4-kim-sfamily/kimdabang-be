package com.kimdabang.kdbserver.mobileGift.mobileGift.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.mobileGift.mobileGift.application.MobileGifticonService;
import com.kimdabang.kdbserver.mobileGift.mobileGift.dto.in.MobileGifticonAddRequestDto;
import com.kimdabang.kdbserver.mobileGift.mobileGift.vo.MobileGifticonAddRequestVo;
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
@RequestMapping("/api/v1/MobileGifticon")
public class MobileGifticonController {

    private final MobileGifticonService mobileGifticonService;

    @PostMapping
    public CommonResponseEntity<Void> createMobileGifticon(@RequestBody MobileGifticonAddRequestVo mobileGifticonAddRequestVo) {
        log.info("mobileGifticonAddRequestVo = {}", mobileGifticonAddRequestVo);
        MobileGifticonAddRequestDto mobileGifticonAddRequestDto = MobileGifticonAddRequestDto.builder()
                .number(mobileGifticonAddRequestVo.getNumber())
                .price(mobileGifticonAddRequestVo.getPrice())
                .build();
        mobileGifticonService.addMobileGifticon(mobileGifticonAddRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "모바일 상품권 등록 성공",
                null
        );
    }
}
