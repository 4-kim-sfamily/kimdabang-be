package com.kimdabang.kdbserver.coupon.coupon.domain;

import com.kimdabang.kdbserver.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.time.Period;

@Getter
@Entity
@ToString
@NoArgsConstructor
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("쿠폰 이름")
    @Column(nullable = false, length = 30)
    private String name;

    @Comment("쿠폰 타입")
    @Enumerated(EnumType.STRING)
    private CouponType couponType;

    @Comment("쿠폰 유효 기간")
    @Column(nullable = false)
    private LocalDateTime expiredDate;

    @Comment("쿠폰 할인률/금액")
    private int value;

    @Comment("쿠폰 유효 기간(년)")
    @Column(nullable = false)
    private String validityYear;

    @Comment("쿠폰 유효 기간(월)")
    @Column(nullable = false)
    private String validityMonth;

    @Comment("쿠폰 유효 기간(일)")
    @Column(nullable = false)
    private String validityDay;

    @Builder
    public Coupon(Long id, String name, CouponType couponType, LocalDateTime expiredDate, int value, String validityYear, String validityMonth, String validityDay) {
        this.id = id;
        this.name = name;
        this.couponType = couponType;
        this.expiredDate = expiredDate;
        this.value = value;
        this.validityYear = validityYear;
        this.validityMonth = validityMonth;
        this.validityDay = validityDay;
    }
}
