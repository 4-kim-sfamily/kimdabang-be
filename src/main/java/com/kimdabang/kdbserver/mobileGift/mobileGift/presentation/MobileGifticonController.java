package com.kimdabang.kdbserver.mobileGift.mobileGift.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.mobileGift.mobileGift.application.MobileGifticonService;
import com.kimdabang.kdbserver.mobileGift.mobileGift.dto.in.MobileGifticonAddRequestDto;
import com.kimdabang.kdbserver.mobileGift.mobileGift.dto.in.MobileGifticonUpdateRequestDto;
import com.kimdabang.kdbserver.mobileGift.mobileGift.vo.MobileGifticonAddRequestVo;
import com.kimdabang.kdbserver.mobileGift.mobileGift.vo.MobileGifticonUpdateRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public CommonResponseEntity<Void> updateMobileGifticon(@RequestBody MobileGifticonUpdateRequestVo mobileGifticonUpdateRequestVo) {
        log.info("mobileGifticonUpdateRequestVo = {}", mobileGifticonUpdateRequestVo);
        MobileGifticonUpdateRequestDto mobileGifticonUpdateRequestDto = MobileGifticonUpdateRequestDto.builder()
                .id(mobileGifticonUpdateRequestVo.getId())
                .number(mobileGifticonUpdateRequestVo.getNumber())
                .price(mobileGifticonUpdateRequestVo.getPrice())
                .build();
        mobileGifticonService.updateMobileGifticon(mobileGifticonUpdateRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "모바일 상품권 업데이트 성공",
                null
        );
    }

    @DeleteMapping
    public CommonResponseEntity<Void> deleteMobileGifticon(@RequestParam Long id) {
        log.info("mobileGifticon = {}", id);
        mobileGifticonService.deleteMobileGifticon(id);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "모바일 상품권 삭제 성공",
                null
        );
    }
}
