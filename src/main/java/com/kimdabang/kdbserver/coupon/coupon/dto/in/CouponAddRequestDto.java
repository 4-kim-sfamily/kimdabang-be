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
    private String validityYear;
    private String validityMonth;
    private String validityDay;

    public Coupon toEntity() {
        return Coupon.builder()
                .name(name)
                .couponType(couponType)
                .expiredDate(expiredDate)
                .value(value)
                .validityYear(validityYear)
                .validityMonth(validityMonth)
                .validityDay(validityDay)
                .build();
    }

    @Builder
    public CouponAddRequestDto(String name, CouponType couponType, LocalDateTime expiredDate, int value, String validityYear, String validityMonth, String validityDay) {
        this.name = name;
        this.couponType = couponType;
        this.expiredDate = expiredDate;
        this.value = value;
        this.validityYear = validityYear;
        this.validityMonth = validityMonth;
        this.validityDay = validityDay;
    }
}
