package com.kimdabang.kdbserver.coupon.userEnrollCoupon.dto.in;

import com.kimdabang.kdbserver.coupon.coupon.domain.Coupon;
import com.kimdabang.kdbserver.coupon.userEnrollCoupon.domain.UserEnrollCoupon;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class UserEnrollCouponAddRequestDto {

    private Long couponId;
    private LocalDateTime createdAt;
    private LocalDateTime expiredDate;

    public UserEnrollCoupon toEntity(String uuid, LocalDateTime createdAt, LocalDateTime expiredDate, Coupon coupon) {
        return UserEnrollCoupon.builder()
                .uuid(uuid)
                .coupon(coupon)
                .createdAt(createdAt)
                .isUsed(false)
                .usedAt(null)
                .expiredDate(expiredDate)
                .build();
    }

    @Builder
    public UserEnrollCouponAddRequestDto(Long couponId, LocalDateTime createdAt, LocalDateTime expiredDate) {
        this.couponId = couponId;
        this.createdAt = createdAt;
        this.expiredDate = expiredDate;
    }
}
