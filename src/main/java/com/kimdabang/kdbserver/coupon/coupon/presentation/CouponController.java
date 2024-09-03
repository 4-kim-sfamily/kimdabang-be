package com.kimdabang.kdbserver.coupon.coupon.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.coupon.coupon.application.CouponService;
import com.kimdabang.kdbserver.coupon.coupon.dto.in.CouponRequestDto;
import com.kimdabang.kdbserver.coupon.coupon.dto.out.CouponResponseDto;
import com.kimdabang.kdbserver.coupon.coupon.vo.CouponRequestVo;
import com.kimdabang.kdbserver.coupon.coupon.vo.CouponResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coupon")
public class CouponController {

    private final CouponService couponService;

    @PostMapping
    public CommonResponseEntity<Void> createCoupon(@RequestBody CouponRequestVo couponRequestVo) {
        log.info("couponRequestVo = {}", couponRequestVo);
        CouponRequestDto couponRequestDto = CouponRequestDto.builder()
                .name(couponRequestVo.getName())
                .number(couponRequestVo.getNumber())
                .code(couponRequestVo.getCode())
                .couponType(couponRequestVo.getCouponType())
                .expiredDate(couponRequestVo.getExpiredDate())
                .value(couponRequestVo.getValue())
                .build();
        couponService.addCoupon(couponRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "쿠폰 등록 성공",
                null
        );
    }

    @PutMapping
    public CommonResponseEntity<Void> updateCoupon(@RequestBody CouponRequestVo couponRequestVo) {
        log.info("couponRequestVo = {}", couponRequestVo);
        CouponRequestDto couponRequestDto = CouponRequestDto.builder()
                .name(couponRequestVo.getName())
                .number(couponRequestVo.getNumber())
                .code(couponRequestVo.getCode())
                .couponType(couponRequestVo.getCouponType())
                .expiredDate(couponRequestVo.getExpiredDate())
                .value(couponRequestVo.getValue())
                .build();
        couponService.updateCoupon(couponRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "쿠폰 업데이트 성공",
                null
        );
    }

    @DeleteMapping
    public CommonResponseEntity<Void> deleteCoupon(@RequestParam String code) {
        log.info("couponCode = {}", code);
        couponService.deleteCoupon(code);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "쿠폰 삭제 성공",
                null
        );
    }

    @GetMapping
    public CommonResponseEntity<List<CouponResponseVo>> getCoupons() {
        List<CouponResponseDto> couponResponseDtos = couponService.getCoupons();
        List<CouponResponseVo> couponResponseVos = couponResponseDtos.stream()
                .map(CouponResponseDto::toResponseVo)
                .toList();
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "쿠폰 전체 조회 성공",
                couponResponseVos
        );
    }

    @GetMapping("/{code}")
    public CommonResponseEntity<CouponResponseVo> getCoupon(@PathVariable String code) {
        CouponResponseDto couponResponseDto = couponService.getCoupon(code);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "쿠폰 조회 성공",
                couponResponseDto.toResponseVo()
        );
    }
}
