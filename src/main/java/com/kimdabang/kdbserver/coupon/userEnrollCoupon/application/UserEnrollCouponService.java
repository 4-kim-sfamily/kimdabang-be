package com.kimdabang.kdbserver.coupon.userEnrollCoupon.application;

import com.kimdabang.kdbserver.coupon.coupon.dto.out.CouponResponseDto;
import com.kimdabang.kdbserver.coupon.userEnrollCoupon.dto.in.UserEnrollCouponAddRequestDto;
import com.kimdabang.kdbserver.coupon.userEnrollCoupon.dto.in.UserEnrollCouponUpdateRequestDto;
import com.kimdabang.kdbserver.coupon.userEnrollCoupon.dto.out.UserEnrollCouponResponseDto;

import java.util.List;

public interface UserEnrollCouponService {

    void addUserEnrollCoupon(UserEnrollCouponAddRequestDto userEnrollCouponAddRequestDto, String Authorization);
    void updateUserEnrollCoupon(UserEnrollCouponUpdateRequestDto userEnrollCouponRequestDto, String Authorization);
    void deleteUserEnrollCoupon(Long id);
//    UserEnrollCouponResponseDto getOneUserEnrollCoupon(Long id);
    List<UserEnrollCouponResponseDto> getAllUserEnrollCoupon(String Authorization);
    Long countEnrollCoupon(String Authorization);
    List<CouponResponseDto> getNotEnrolledCoupon(String Authorization);
    List<UserEnrollCouponResponseDto> getEnrolledCoupon(String Authorization);

}
