package com.kimdabang.kdbserver.coupon.coupon.application;

import com.kimdabang.kdbserver.coupon.coupon.domain.Coupon;
import com.kimdabang.kdbserver.coupon.coupon.dto.in.CouponRequestDto;
import com.kimdabang.kdbserver.coupon.coupon.dto.out.CouponResponseDto;
import com.kimdabang.kdbserver.coupon.coupon.infrastructure.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public void addCoupon(CouponRequestDto couponRequestDto) {
        couponRepository.save(couponRequestDto.toEntity());
    }

    @Override
    public void updateCoupon(CouponRequestDto couponRequestDto) {
        couponRepository.findById(couponRequestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 쿠폰이 존재하지 않습니다."));

        couponRepository.save(couponRequestDto.toEntity());
    }

    @Override
    public void deleteCoupon(Long couponId) {
        Coupon deleteCoupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("해당 쿠폰이 존재하지 않습니다."));

        couponRepository.delete(deleteCoupon);
    }

    @Override
    public CouponResponseDto getCoupon(Long couponId) {
        Coupon getCoupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("해당 쿠폰이 존재하지 않습니다."));

        return CouponResponseDto.builder()
                .id(getCoupon.getId())
                .name(getCoupon.getName())
                .number(getCoupon.getNumber())
                .code(getCoupon.getCode())
                .couponType(getCoupon.getCouponType())
                .expiredDate(getCoupon.getExpiredDate())
                .value(getCoupon.getValue())
                .build();
    }

    @Override
    public List<CouponResponseDto> getCoupons() {
        List<Coupon> coupons = couponRepository.findAll();

            return coupons.stream()
                    .map(coupon -> CouponResponseDto.builder()
                            .id(coupon.getId())
                            .name(coupon.getName())
                            .number(coupon.getNumber())
                            .code(coupon.getCode())
                            .couponType(coupon.getCouponType())
                            .expiredDate(coupon.getExpiredDate())
                            .value(coupon.getValue())
                            .build())
                    .toList();
    }
}
