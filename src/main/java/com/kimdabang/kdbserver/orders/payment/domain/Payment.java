package com.kimdabang.kdbserver.orders.payment.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("결제 번호")
    @Column(nullable = false, updatable = false)
    private Long paymentCode;

    @Comment("결제일")
    @Column(nullable = false)
    private Date paymentDate;

    @Comment("쿠폰 번호")
    @Column(nullable = false)
    private Long couponId;

    @Comment("합계 금액")
    @Column(nullable = false)
    private Integer totalPrice;

    @Comment("할인 금액")
    @Column(nullable = false)
    private Integer discountPrice;

    @Comment("배송비")
    @Column(nullable = false)
    private Integer shippingPrice;

    @Comment("실결제 금액")
    @Column(nullable = false)
    private Integer amount;

    @Comment("결제 방식")
    @Column(nullable = false)
    private String method;

    @Builder
    public Payment(
            Long paymentCode,
            Date paymentDate,
            Long couponId,
            Integer totalPrice,
            Integer discountPrice,
            Integer shippingPrice,
            Integer amount,
            String method
    ) {
        this.paymentCode = paymentCode;
        this.paymentDate = paymentDate;
        this.couponId = couponId;
        this.totalPrice = totalPrice;
        this.discountPrice = discountPrice;
        this.shippingPrice = shippingPrice;
        this.amount = amount;
        this.method = method;
    }
}
