package com.kimdabang.kdbserver.restock.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
public class Restock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("알림 신청 날짜")
    @Column(nullable = false)
    private Date restockDate;

    @Comment("유저 UUID")
    @Column(nullable = false)
    private String uuid;

    @Comment("알림 상태")
    @Column(nullable = false)
    private Boolean status;

    @Comment("상품 코드")
    @Column(nullable = false)
    private String productCode;

    @Comment("알림 전송 날짜")
    @Column(nullable = true)
    private Date calledDate;



    @Builder
    public Restock(
            Long id,
            Date restockDate,
            String uuid,
            Boolean status,
            String productCode,
            Date calledDate

    ) {
        this.id = id;
        this.restockDate = restockDate;
        this.uuid = uuid;
        this.status = status;
        this.productCode = productCode;
        this.calledDate = calledDate;
    }
}
