package com.kimdabang.kdbserver.coupon.userEnrollCoupon.domain;

import com.kimdabang.kdbserver.common.entity.BaseEntity;
import com.kimdabang.kdbserver.coupon.coupon.domain.Coupon;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@ToString
@NoArgsConstructor
public class UserEnrollCoupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고객 쿠폰 pk")
    private Long id;

    @Comment("고객 uuid")
    @Column(nullable = false)
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @Comment("쿠폰 등록일")
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Comment("사용 여부")
    @Column(nullable = false)
    private Boolean isUsed;

    @Comment("사용 일시")
    private LocalDateTime usedAt;

    @Comment("쿠폰 만료일")
    @Column(nullable = false)
    private LocalDateTime expiredDate;

    @Builder
    public UserEnrollCoupon(Long id, String uuid, Coupon coupon, LocalDateTime createdAt, Boolean isUsed, LocalDateTime usedAt, LocalDateTime expiredDate) {
        this.id = id;
        this.uuid = uuid;
        this.coupon = coupon;
        this.createdAt = createdAt;
        this.isUsed = isUsed;
        this.usedAt = usedAt;
        this.expiredDate = expiredDate;
    }
}
