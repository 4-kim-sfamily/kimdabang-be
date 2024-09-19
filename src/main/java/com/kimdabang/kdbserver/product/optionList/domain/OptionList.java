package com.kimdabang.kdbserver.product.optionList.domain;

import com.kimdabang.kdbserver.common.entity.BaseEntity;
import com.kimdabang.kdbserver.product.option.domain.Option;
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
@Table(name = "option_list")
public class OptionList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("상품 코드")
    @Column(nullable = false)
    private String productCode;

    @Comment("옵션 pk")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "options_id")
    private Option option;


}
