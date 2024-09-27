package com.kimdabang.kdbserver.mobileGift.userEnrollMG.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.mobileGift.userEnrollMG.application.UserEnrollMobileGifticonService;
import com.kimdabang.kdbserver.mobileGift.userEnrollMG.dto.in.UserEnrollMobileGifticonAddRequestDto;
import com.kimdabang.kdbserver.mobileGift.userEnrollMG.dto.out.UserEnrollMobileGifticonResponseDto;
import com.kimdabang.kdbserver.mobileGift.userEnrollMG.vo.UserEnrollMobileGifticonAddRequestVo;
import com.kimdabang.kdbserver.mobileGift.userEnrollMG.vo.UserEnrollMobileGifticonResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-enroll-mobile-gifticon")
public class UserEnrollMobileGifticonController {

    private final UserEnrollMobileGifticonService userEnrollMobileGifticonService;

    @PostMapping
    public CommonResponseEntity<Void> createUserEnrollMobileGifticon(
            @RequestHeader ("Authorization") String authorization,
            @RequestBody UserEnrollMobileGifticonAddRequestVo userEnrollMobileGifticonAddRequestVo) {
        log.info("userEnrollMobileGifticonAddRequestVo = {}", userEnrollMobileGifticonAddRequestVo);
        UserEnrollMobileGifticonAddRequestDto userEnrollMobileGifticonAddRequestDto = UserEnrollMobileGifticonAddRequestDto.builder()
                .mobileGifticonId(userEnrollMobileGifticonAddRequestVo.getMobileGifticonId())
                .build();
        userEnrollMobileGifticonService.addUserEnrollMobileGifticon(userEnrollMobileGifticonAddRequestDto, authorization);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "유저 모바일상품권 등록 성공",
                null
        );
    }

    @DeleteMapping
    public CommonResponseEntity<Void> deleteUserEnrollMobileGifticon(
            @RequestParam Long id,
            @RequestHeader ("Authorization") String authorization) {
        log.info("userEnrollMobileGifticonId = {}", id);
        userEnrollMobileGifticonService.deleteUserEnrollMobileGifticon(id, authorization);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "유저 모바일상품권 삭제 성공",
                null
        );
    }

    @GetMapping
    public CommonResponseEntity<List<UserEnrollMobileGifticonResponseVo>> getAllUserEnrollMobileGifticon(@RequestHeader("Authorization") String authorization) {
        List<UserEnrollMobileGifticonResponseDto> userEnrollMobileGifticonResponseDtos = userEnrollMobileGifticonService.getAllUserEnrollMobileGifticon(authorization);
        List<UserEnrollMobileGifticonResponseVo> userEnrollMobileGifticonResponseVos = userEnrollMobileGifticonResponseDtos.stream()
                .map(UserEnrollMobileGifticonResponseDto::toResponseVo)
                .toList();
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "유저 모바일상품권 전체 조회 성공",
                userEnrollMobileGifticonResponseVos
        );
    }

    @GetMapping("/{id}")
    public CommonResponseEntity<UserEnrollMobileGifticonResponseVo> getOneUserEnrollMobileGifticon(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authorization) {
        UserEnrollMobileGifticonResponseDto userEnrollMobileGifticonResponseDto = userEnrollMobileGifticonService.getOneUserEnrollMobileGifticon(id, authorization);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "유저 모바일상품권 조회 성공",
                userEnrollMobileGifticonResponseDto.toResponseVo()
        );
    }
}
