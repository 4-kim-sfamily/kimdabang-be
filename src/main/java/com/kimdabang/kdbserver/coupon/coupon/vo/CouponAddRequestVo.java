package com.kimdabang.kdbserver.coupon.coupon.vo;

import com.kimdabang.kdbserver.coupon.coupon.domain.CouponType;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.Period;

@Getter
@ToString
public class CouponAddRequestVo {
    private String name;
    private CouponType couponType;
    private LocalDateTime expiredDate;
    private int value;
    private String validityYear;
    private String validityMonth;
    private String validityDay;
}
