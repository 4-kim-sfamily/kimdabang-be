package com.kimdabang.kdbserver.product.option.domain;

import com.kimdabang.kdbserver.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Table(name = "options")
public class Option extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "options_id")
    private Long id;

    @Comment("상품 코드")
    @Column(nullable = false)
    private String productCode;

    @Comment("상품 정보")
    @Column(nullable = false)
    private String optionValue;

    @Comment("옵션 깊이")
    @Column(nullable = false)
    private int depth;

    @Comment("부모 옵션")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Option parentOptionsId;

    @Transient
    private List<Option> children; // 자식 옵션

}
