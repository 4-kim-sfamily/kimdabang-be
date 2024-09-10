package com.kimdabang.kdbserver.coupon.coupon.vo;

import com.kimdabang.kdbserver.coupon.coupon.domain.CouponType;
import lombok.*;

import java.time.LocalDateTime;
import java.time.Period;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CouponResponseVo {
    private Long id;
    private String name;
    private CouponType couponType;
    private LocalDateTime expiredDate;
    private int value;
    private Period validityYear;
    private Period validityMonth;
    private Period validityDay;
}
