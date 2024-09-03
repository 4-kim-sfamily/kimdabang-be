package com.kimdabang.kdbserver.coupon.coupon.dto.out;

import com.kimdabang.kdbserver.coupon.coupon.domain.CouponType;
import com.kimdabang.kdbserver.coupon.coupon.vo.CouponResponseVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CouponResponseDto {

    private Long id;
    private String name;
    private CouponType couponType;
    private LocalDateTime expiredDate;
    private int value;

    public CouponResponseVo toResponseVo() {
        return CouponResponseVo.builder()
                .id(id)
                .name(name)
                .couponType(couponType)
                .expiredDate(expiredDate)
                .value(value)
                .build();
    }

    @Builder
    public CouponResponseDto(Long id, String name, CouponType couponType, LocalDateTime expiredDate, int value) {
        this.id = id;
        this.name = name;
        this.couponType = couponType;
        this.expiredDate = expiredDate;
        this.value = value;
    }
}
