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
    private Coupon coupon;
    private LocalDateTime createdAt;
    private Boolean isUsed;
    private LocalDateTime usedAt;
    private LocalDateTime expiredDate;

    public UserEnrollCouponResponseVo toResponseVo() {
        return UserEnrollCouponResponseVo.builder()
                .id(id)
                .uuid(uuid)
                .coupon(coupon)
                .createdAt(createdAt)
                .isUsed(isUsed)
                .usedAt(usedAt)
                .expiredDate(expiredDate)
                .build();
    }

    @Builder
    public UserEnrollCouponResponseDto(Long id, String uuid, Coupon coupon, LocalDateTime createdAt, Boolean isUsed, LocalDateTime usedAt, LocalDateTime expiredDate) {
        this.id = id;
        this.uuid = uuid;
        this.coupon = coupon;
        this.createdAt = createdAt;
        this.isUsed = isUsed;
        this.usedAt = usedAt;
        this.expiredDate = expiredDate;
    }

}
