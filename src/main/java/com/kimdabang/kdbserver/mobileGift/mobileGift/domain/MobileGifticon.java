package com.kimdabang.kdbserver.mobileGift.mobileGift.domain;

import com.kimdabang.kdbserver.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@ToString
@NoArgsConstructor
public class MobileGifticon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("상품권 번호")
    @Column(nullable = false)
    private String number;

    @Comment("상품권 금액")
    @Column(nullable = false)
    private int price;

    @Builder
    public MobileGifticon(Long id, String number, int price) {
        this.id = id;
        this.number = number;
        this.price = price;
    }
}
