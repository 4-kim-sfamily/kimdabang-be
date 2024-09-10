package com.kimdabang.kdbserver.coupon.userEnrollCoupon.vo;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class UserEnrollCouponAddRequestVo {
    private String accessToken;
//    private Coupon coupon;
    private Long couponId;
    private LocalDateTime createdAt;
    private Boolean isUsed;
    private LocalDateTime usedAt;
    private LocalDateTime expiredDate;
}
