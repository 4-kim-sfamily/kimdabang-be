package com.kimdabang.kdbserver.coupon.coupon.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CouponType {

    PERCENTAGE("할인률 쿠폰"),
    FIXED_AMOUNT("할인액 쿠폰");

    private final String couponType;

    @JsonValue
    public String getCouponType() {
        return couponType;
    }

    @JsonCreator
    public static CouponType fromString(String value) {
        for (CouponType couponType: CouponType.values()) {
            if (couponType.couponType.equals(value)) {
                return couponType;
            }
        }
        throw new IllegalArgumentException("UnKnown value: " + value);
    }
}
