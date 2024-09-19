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

    @Comment("옵션별 상품 재고량")
    @Column(nullable = false)
    private Long quantity;

    @Comment("보유해야할 최소 재고량")
    @Column(nullable = false)
    private Long minStock;

    @OneToOne
    @JoinColumn(name = "option_list")
    private OptionList optionList;

}
