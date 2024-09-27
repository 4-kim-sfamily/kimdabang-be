package com.kimdabang.kdbserver.orders.purchase.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("주문일")
    @Column(nullable = false)
    private Date purchaseDate;

    @Comment("주문 번호")
    @Column(nullable = false)
    private Long purchaseCode;

    @Comment("결제 번호")
    @Column(nullable = false)
    private Long paymentCode;

    @Comment("유저 UUID")
    @Column(nullable = false)
    private String userUuid;

    @Comment("주문 상태")
    @Column(nullable = false)
    private String status;

    @Comment("배송지")
    @Column(nullable = false, length = 100)
    private String address;

    @Comment("받는 사람")
    @Column(nullable = false, length = 20)
    private String name;

    @Comment("전화 번호")
    @Column(nullable = false, length = 20)
    private String phone;

    @Builder
    public Purchase(
            Long purchaseCode,
            Date purchaseDate,
            Long paymentCode,
            String userUuid,
            String status,
            String address,
            String name,
            String phone
    ) {
        this.purchaseCode = purchaseCode;
        this.purchaseDate = purchaseDate;
        this.paymentCode = paymentCode;
        this.userUuid = userUuid;
        this.status = status;
        this.address = address;
        this.name = name;
        this.phone = phone;
    }

}
