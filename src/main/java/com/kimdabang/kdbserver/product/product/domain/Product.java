package com.kimdabang.kdbserver.product.product.domain;

import com.kimdabang.kdbserver.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.Date;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Comment("상품 코드")
    @Column(nullable = false, length = 50)
    private String productCode;
    @Comment("상품 이름")
    @Column(nullable = false, length = 100)
    private String productName;
    @Comment("상품 가격")
    @Column(nullable = false)
    private Long productPrice;
    @Comment("상품 설명")
    @Column(nullable = false, length = 100)
    private String description;
    @Comment("상품 출시일")
    @Column(nullable = true, length = 20)
    private Date releaseDate;
    @Comment("상품 내용")
    @Column(nullable = true, length = 1000)
    private String content;
    @Comment("카테고리 pk")
    @Column(nullable = true)
    private Long categoryId;

}
