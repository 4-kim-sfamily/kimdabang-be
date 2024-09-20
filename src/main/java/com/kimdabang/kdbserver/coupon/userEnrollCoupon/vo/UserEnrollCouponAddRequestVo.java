package com.kimdabang.kdbserver.coupon.userEnrollCoupon.vo;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class UserEnrollCouponAddRequestVo {
    private Long couponId;
    private LocalDateTime createdAt;
    private LocalDateTime expiredDate;
}
