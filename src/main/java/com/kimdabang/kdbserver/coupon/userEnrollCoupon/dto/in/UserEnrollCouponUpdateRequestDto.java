package com.kimdabang.kdbserver.coupon.userEnrollCoupon.dto.in;

import com.kimdabang.kdbserver.coupon.userEnrollCoupon.domain.UserEnrollCoupon;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserEnrollCouponUpdateRequestDto {
    private Long id;
    private Boolean isUsed;
    private LocalDateTime usedAt;
    private LocalDateTime expiredDate;

    public UserEnrollCoupon toEntity(UserEnrollCoupon userEnrollCoupon) {
        return UserEnrollCoupon.builder()
                .id(id)
                .uuid(userEnrollCoupon.getUuid())
                .coupon(userEnrollCoupon.getCoupon())
                .isUsed(isUsed)
                .usedAt(usedAt)
                .expiredDate(expiredDate)
                .build();
    }

    @Builder
    public UserEnrollCouponUpdateRequestDto(Long id, Boolean isUsed, LocalDateTime usedAt, LocalDateTime expiredDate) {
        this.id = id;
        this.isUsed = isUsed;
        this.usedAt = usedAt;
        this.expiredDate = expiredDate;
    }

}
