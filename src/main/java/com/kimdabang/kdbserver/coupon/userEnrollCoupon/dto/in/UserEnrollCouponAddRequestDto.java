package com.kimdabang.kdbserver.coupon.userEnrollCoupon.dto.in;

import com.kimdabang.kdbserver.coupon.coupon.domain.Coupon;
import com.kimdabang.kdbserver.coupon.userEnrollCoupon.domain.UserEnrollCoupon;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class
UserEnrollCouponAddRequestDto {

    private String accessToken;
//    private Coupon coupon;
    private Long couponId;
    private LocalDateTime createdAt;
    private Boolean isUsed;
    private LocalDateTime usedAt;
    private LocalDateTime expiredDate;

    public UserEnrollCoupon toEntity(String uuid, LocalDateTime createdAt, LocalDateTime expiredDate, Coupon coupon) {
        return UserEnrollCoupon.builder()
                .uuid(uuid)
                .coupon(coupon)
                .createdAt(createdAt)
                .isUsed(isUsed)
                .usedAt(usedAt)
                .expiredDate(expiredDate)
                .build();
    }

    @Builder
    public UserEnrollCouponAddRequestDto(String accessToken, Long couponId, LocalDateTime createdAt, Boolean isUsed, LocalDateTime usedAt, LocalDateTime expiredDate) {
        this.accessToken = accessToken;
        this.couponId = couponId;
        this.createdAt = createdAt;
        this.isUsed = isUsed;
        this.usedAt = usedAt;
        this.expiredDate = expiredDate;
    }
}
