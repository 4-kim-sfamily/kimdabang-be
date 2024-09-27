package com.kimdabang.kdbserver.coupon.userEnrollCoupon.vo;

import com.kimdabang.kdbserver.coupon.coupon.domain.Coupon;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEnrollCouponResponseVo {

    private Long id;
    private String uuid;
    private Long couponId;
    private LocalDateTime createdAt;
    private Boolean isUsed;
    private LocalDateTime usedAt;
    private LocalDateTime expiredDate;
}
