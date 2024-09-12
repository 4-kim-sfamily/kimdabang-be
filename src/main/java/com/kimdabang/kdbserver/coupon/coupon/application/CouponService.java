package com.kimdabang.kdbserver.coupon.coupon.application;

import com.kimdabang.kdbserver.coupon.coupon.dto.in.CouponAddRequestDto;
import com.kimdabang.kdbserver.coupon.coupon.dto.in.CouponUpdateRequestDto;
import com.kimdabang.kdbserver.coupon.coupon.dto.out.CouponResponseDto;

import java.util.List;

public interface CouponService {

    void addCoupon(CouponAddRequestDto couponRequestAddDto);
    void updateCoupon(CouponUpdateRequestDto couponRequestUpdateDto);
    void deleteCoupon(Long id);
    CouponResponseDto getOneCoupon(Long id);
    List<CouponResponseDto> getAllCoupon();

}
