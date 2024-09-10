package com.kimdabang.kdbserver.coupon.coupon.dto.out;

import com.kimdabang.kdbserver.coupon.coupon.domain.CouponType;
import com.kimdabang.kdbserver.coupon.coupon.vo.CouponResponseVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.Period;

@Getter
public class CouponResponseDto {

    private Long id;
    private String name;
    private CouponType couponType;
    private LocalDateTime expiredDate;
    private int value;
    private Period validityYear;
    private Period validityMonth;
    private Period validityDay;

    public CouponResponseVo toResponseVo() {
        return CouponResponseVo.builder()
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
    public CouponResponseDto(Long id, String name, CouponType couponType, LocalDateTime expiredDate, int value, Period validityYear, Period validityMonth, Period validityDay) {
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
