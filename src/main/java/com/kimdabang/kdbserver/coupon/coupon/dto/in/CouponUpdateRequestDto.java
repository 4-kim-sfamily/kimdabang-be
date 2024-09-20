package com.kimdabang.kdbserver.coupon.coupon.dto.in;

import com.kimdabang.kdbserver.coupon.coupon.domain.Coupon;
import com.kimdabang.kdbserver.coupon.coupon.domain.CouponType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.Period;

@Getter
public class CouponUpdateRequestDto {

    private Long id;
    private String name;
    private CouponType couponType;
    private LocalDateTime expiredDate;
    private int value;
    private String validityYear;
    private String validityMonth;
    private String validityDay;

    public Coupon toEntity() {
        return Coupon.builder()
                .id(id)
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
    public CouponUpdateRequestDto(Long id, String name, CouponType couponType, LocalDateTime expiredDate, int value, String validityYear, String validityMonth, String validityDay) {
        this.id = id;
        this.name = name;
        this.couponType = couponType;
        this.expiredDate = expiredDate;
        this.value = value;
        this.validityYear = validityYear;
        this.validityMonth = validityMonth;
        this.validityDay = validityDay;
    }
}
