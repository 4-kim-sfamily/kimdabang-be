package com.kimdabang.kdbserver.coupon.coupon.application;

import com.kimdabang.kdbserver.coupon.coupon.dto.in.CouponRequestDto;
import com.kimdabang.kdbserver.coupon.coupon.dto.out.CouponResponseDto;

import java.util.List;

public interface CouponService {

    void addCoupon(CouponRequestDto couponRequestDto);
    void updateCoupon(CouponRequestDto couponRequestDto);
    void deleteCoupon(Long couponId);
    CouponResponseDto getCoupon(Long couponId);
    List<CouponResponseDto> getCoupons();

}
