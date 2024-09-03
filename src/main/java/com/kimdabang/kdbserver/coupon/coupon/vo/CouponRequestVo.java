package com.kimdabang.kdbserver.coupon.coupon.vo;

import com.kimdabang.kdbserver.coupon.coupon.domain.CouponType;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class CouponRequestVo {
    private String name;
    private CouponType couponType;
    private LocalDateTime expiredDate;
    private int value;

}
