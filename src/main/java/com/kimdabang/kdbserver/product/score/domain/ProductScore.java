package com.kimdabang.kdbserver.product.score.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@NoArgsConstructor
public class ProductScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("상품 코드")
    @Column(nullable = false, length = 50)
    private String productCode;

    @Comment("카테고리 id")
    @Column(nullable = false)
    private Long categoryId;

    @Comment("리뷰 수")
    @Column(nullable = false)
    private Integer purchaseCount;

    @Comment("리뷰 수")
    @Column(nullable = false)
    private Integer reviewCount;

    @Comment("평점")
    @Column(nullable = false)
    private Double rating;

    @Comment("좋아요 수")
    @Column(nullable = false)
    private Integer favoriteCount;

    @Comment("총점")
    @Column(nullable = false)
    private Double score;


    @Builder
    public ProductScore(
            Long id,
            String productCode,
            Long categoryId,
            Integer purchaseCount,
            Integer reviewCount,
            Double rating,
            Integer favoriteCount,
            Double score
    ) {
        this.id = id;
        this.productCode = productCode;
        this.categoryId = categoryId;
        this.purchaseCount = purchaseCount;
        this.reviewCount = reviewCount;
        this.rating = rating;
        this.favoriteCount = favoriteCount;
        this.score = score;
    }
}
