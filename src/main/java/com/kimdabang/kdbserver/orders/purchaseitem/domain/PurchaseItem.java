package com.kimdabang.kdbserver.orders.purchaseitem.domain;

import com.kimdabang.kdbserver.orders.purchase.domain.Purchase;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@NoArgsConstructor
public class PurchaseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("상품 코드")
    @Column(nullable = false, length = 50)
    private String productCode;

    @Comment("옵션 리스트 id")
    @Column(nullable = false)
    private Long optionsId;

    @Comment("옵션 리스트")
    @Column(nullable = false)
    private String options;

    @Comment("수량")
    @Column(nullable = false)
    private Integer quantity;

    @Comment("금액")
    @Column(nullable = false)
    private Integer price;

    @Comment("주문")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Purchase purchase;

    @Builder
    public PurchaseItem(
            String productCode,
            Long optionsId,
            String options,
            Integer quantity,
            Integer price,
            Purchase purchase
    ) {
        this.productCode = productCode;
        this.optionsId = optionsId;
        this.options = options;
        this.quantity = quantity;
        this.price = price;
        this.purchase = purchase;
    }
}
