package com.kimdabang.kdbserver.coupon.coupon.dto.in;

import com.kimdabang.kdbserver.coupon.coupon.domain.Coupon;
import com.kimdabang.kdbserver.coupon.coupon.domain.CouponType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.Period;

@Getter
public class CouponAddRequestDto {

    private String name;
    private CouponType couponType;
    private LocalDateTime expiredDate;
    private int value;
    private long validity;

    public Coupon toEntity() {
        return Coupon.builder()
                .name(name)
                .couponType(couponType)
                .expiredDate(expiredDate)
                .value(value)
                .validity(validity)
                .build();
    }

    @Builder
    public CouponAddRequestDto(String name, CouponType couponType, LocalDateTime expiredDate, int value, long validity) {
        this.name = name;
        this.couponType = couponType;
        this.expiredDate = expiredDate;
        this.value = value;
        this.validity = validity;
    }
}
