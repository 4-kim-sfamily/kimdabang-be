package com.kimdabang.kdbserver.coupon.userEnrollCoupon.dto.out;

import com.kimdabang.kdbserver.coupon.coupon.domain.Coupon;
import com.kimdabang.kdbserver.coupon.userEnrollCoupon.vo.UserEnrollCouponResponseVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserEnrollCouponResponseDto {

    private Long id;
    private String uuid;
    private Long couponId;
    private LocalDateTime createdAt;
    private Boolean isUsed;
    private LocalDateTime usedAt;
    private LocalDateTime expiredDate;

    public UserEnrollCouponResponseVo toResponseVo() {
        return UserEnrollCouponResponseVo.builder()
                .id(id)
                .uuid(uuid)
                .couponId(couponId)
                .createdAt(createdAt)
                .isUsed(isUsed)
                .usedAt(usedAt)
                .expiredDate(expiredDate)
                .build();
    }

    @Builder
    public UserEnrollCouponResponseDto(Long id, String uuid, Long couponId, LocalDateTime createdAt, Boolean isUsed, LocalDateTime usedAt, LocalDateTime expiredDate) {
        this.id = id;
        this.uuid = uuid;
        this.couponId = couponId;
        this.createdAt = createdAt;
        this.isUsed = isUsed;
        this.usedAt = usedAt;
        this.expiredDate = expiredDate;
    }

}
