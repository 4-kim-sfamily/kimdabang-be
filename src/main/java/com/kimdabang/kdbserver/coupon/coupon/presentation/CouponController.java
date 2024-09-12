package com.kimdabang.kdbserver.coupon.coupon.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.coupon.coupon.application.CouponService;
import com.kimdabang.kdbserver.coupon.coupon.dto.in.CouponAddRequestDto;
import com.kimdabang.kdbserver.coupon.coupon.dto.in.CouponUpdateRequestDto;
import com.kimdabang.kdbserver.coupon.coupon.dto.out.CouponResponseDto;
import com.kimdabang.kdbserver.coupon.coupon.vo.CouponAddRequestVo;
import com.kimdabang.kdbserver.coupon.coupon.vo.CouponUpdateRequestVo;
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
    public CommonResponseEntity<Void> createCoupon(@RequestBody CouponAddRequestVo couponAddRequestVo) {
        log.info("couponAddRequestVo = {}", couponAddRequestVo);
        CouponAddRequestDto couponAddRequestDto = CouponAddRequestDto.builder()
                .name(couponAddRequestVo.getName())
                .couponType(couponAddRequestVo.getCouponType())
                .expiredDate(couponAddRequestVo.getExpiredDate())
                .value(couponAddRequestVo.getValue())
                .validityYear(couponAddRequestVo.getValidityYear())
                .validityMonth(couponAddRequestVo.getValidityMonth())
                .validityDay(couponAddRequestVo.getValidityDay())
                .build();
        couponService.addCoupon(couponAddRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "쿠폰 등록 성공",
                null
        );
    }

    @PutMapping
    public CommonResponseEntity<Void> updateCoupon(@RequestBody CouponUpdateRequestVo couponUpdateRequestVo) {
        log.info("couponUpdateRequestVo = {}", couponUpdateRequestVo);
        CouponUpdateRequestDto couponUpdateRequestDto = CouponUpdateRequestDto.builder()
                .id(couponUpdateRequestVo.getId())
                .name(couponUpdateRequestVo.getName())
                .couponType(couponUpdateRequestVo.getCouponType())
                .expiredDate(couponUpdateRequestVo.getExpiredDate())
                .value(couponUpdateRequestVo.getValue())
                .validityYear(couponUpdateRequestVo.getValidityYear())
                .validityMonth(couponUpdateRequestVo.getValidityMonth())
                .validityDay(couponUpdateRequestVo.getValidityDay())
                .build();
        couponService.updateCoupon(couponUpdateRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "쿠폰 업데이트 성공",
                null
        );
    }

    @DeleteMapping
    public CommonResponseEntity<Void> deleteCoupon(@RequestParam Long id) {
        log.info("couponId = {}", id);
        couponService.deleteCoupon(id);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "쿠폰 삭제 성공",
                null
        );
    }

    @GetMapping
    public CommonResponseEntity<List<CouponResponseVo>> getAllCoupon() {
        List<CouponResponseDto> couponResponseDtos = couponService.getAllCoupon();
        List<CouponResponseVo> couponResponseVos = couponResponseDtos.stream()
                .map(CouponResponseDto::toResponseVo)
                .toList();
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "쿠폰 전체 조회 성공",
                couponResponseVos
        );
    }

    @GetMapping("/{id}")
    public CommonResponseEntity<CouponResponseVo> getOneCoupon(@PathVariable Long id) {
        CouponResponseDto couponResponseDto = couponService.getOneCoupon(id);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "쿠폰 조회 성공",
                couponResponseDto.toResponseVo()
        );
    }
}
