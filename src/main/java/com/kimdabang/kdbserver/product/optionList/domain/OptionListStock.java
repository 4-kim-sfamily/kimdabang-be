package com.kimdabang.kdbserver.product.optionList.domain;

import com.kimdabang.kdbserver.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "option_list_stock")
public class OptionListStock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("상품 코드")
    @Column(nullable = false)
    private String productCode;

    @Comment("옵션 ID")
    @Column(nullable = false)
    private Long optionId;

    @Comment("옵션별 상품 판매 가능 여부")
    @Column(nullable = false)
    private Boolean state;

    @Comment("옵션별 상품 재고량")
    @Column(nullable = false)
    private Long quantity;

    @Comment("보유해야할 최소 재고량")
    @Column(nullable = false)
    private Long minStock;

    @Comment("옵션 별 변동 가격")
    @Column(nullable = false)
    private int variablePrice;

}
