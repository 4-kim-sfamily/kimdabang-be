package com.kimdabang.kdbserver.coupon.userEnrollCoupon.dto.in;

import com.kimdabang.kdbserver.coupon.userEnrollCoupon.domain.UserEnrollCoupon;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserEnrollCouponUsingRequestDto {
    private Long id;
    private Boolean isUsed;
    private LocalDateTime usedAt;

    public UserEnrollCoupon toEntity(UserEnrollCoupon userEnrollCoupon) {
        return UserEnrollCoupon.builder()
                .id(id)
                .uuid(userEnrollCoupon.getUuid())
                .coupon(userEnrollCoupon.getCoupon())
                .createdAt(userEnrollCoupon.getCreatedAt())
                .isUsed(isUsed)
                .usedAt(usedAt)
                .expiredDate(userEnrollCoupon.getExpiredDate())
                .build();
    }
    @Builder
    public UserEnrollCouponUsingRequestDto(Long id, Boolean isUsed, LocalDateTime usedAt) {
        this.id = id;
        this.isUsed = isUsed;
        this.usedAt = usedAt;
    }

}
