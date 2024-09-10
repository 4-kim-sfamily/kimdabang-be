package com.kimdabang.kdbserver.coupon.userEnrollCoupon.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.coupon.userEnrollCoupon.application.UserEnrollCouponService;
import com.kimdabang.kdbserver.coupon.userEnrollCoupon.dto.in.UserEnrollCouponAddRequestDto;
import com.kimdabang.kdbserver.coupon.userEnrollCoupon.dto.in.UserEnrollCouponUpdateRequestDto;
import com.kimdabang.kdbserver.coupon.userEnrollCoupon.vo.UserEnrollCouponAddRequestVo;
import com.kimdabang.kdbserver.coupon.userEnrollCoupon.vo.UserEnrollCouponUpdateRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-enroll-coupon")
public class UserEnrollCouponController {

    private final UserEnrollCouponService userEnrollCouponService;

    @PostMapping
    public CommonResponseEntity<Void> createUserEnrollCoupon(@RequestBody UserEnrollCouponAddRequestVo userEnrollCouponRequestVo) {
        log.info("userEnrollCouponRequestVo = {}", userEnrollCouponRequestVo);
        UserEnrollCouponAddRequestDto userEnrollCouponAddRequestDto = UserEnrollCouponAddRequestDto.builder()
                .accessToken(userEnrollCouponRequestVo.getAccessToken())
                .couponId(userEnrollCouponRequestVo.getCouponId())
                .createdAt(userEnrollCouponRequestVo.getCreatedAt())
                .isUsed(userEnrollCouponRequestVo.getIsUsed())
                .usedAt(userEnrollCouponRequestVo.getUsedAt())
                .expiredDate(userEnrollCouponRequestVo.getExpiredDate())
                .build();
        userEnrollCouponService.addUserEnrollCoupon(userEnrollCouponAddRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "유저 쿠폰 등록 성공",
                null
                );
    }

    @PutMapping
    public CommonResponseEntity<Void> updateUserEnrollCoupon (@RequestBody UserEnrollCouponUpdateRequestVo userEnrollCouponUpdateRequestVo) {
        log.info("userEnrollCouponRequestVo = {}", userEnrollCouponUpdateRequestVo);
        UserEnrollCouponUpdateRequestDto userEnrollCouponUpdateRequestDto = UserEnrollCouponUpdateRequestDto.builder()
                .isUsed(userEnrollCouponUpdateRequestVo.getIsUsed())
                .usedAt(userEnrollCouponUpdateRequestVo.getUsedAt())
                .expiredDate(userEnrollCouponUpdateRequestVo.getExpiredDate())
                .build();
        userEnrollCouponService.updateUserEnrollCoupon(userEnrollCouponUpdateRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "유저 쿠폰 등록 업데이트 성공",
                null
        );
    }

    @DeleteMapping
    public CommonResponseEntity<Void> deleteCoupon(@RequestParam Long id) {
        log.info("userEnrollCouponId = {}", id);
        userEnrollCouponService.deleteUserEnrollCoupon(id);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "유저 쿠폰 등록 삭제 성공",
                null
        );
    }
}
