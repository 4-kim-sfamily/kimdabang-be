package com.kimdabang.kdbserver.review.review.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;

import java.util.Date;

@Getter
@Entity
@ToString
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("리뷰 코드")
    @Column(nullable = false)
    private Long reviewCode;

    @Comment("상품 코드")
    @Column(nullable = false, length = 50)
    private String productCode;

    @Comment("옵션 리스트")
    @Column(nullable = false)
    private String options;

    @Comment("작성일")
    @Column(nullable = false)
    private Date creationDate;

    @Comment("별점")
    @Column(nullable = false)
    private Integer rating;

    @Comment("고객 UUID")
    @Column(nullable = false)
    private String uuid;

    @Comment("고객 닉네임")
    @Column(nullable = false)
    private String nickname;

    @Comment("리뷰")
    @Column(nullable = true, length = 300)
    private String text;

    @Builder
    public Review(
            Long reviewCode,
            String productCode,
            String options,
            Date creationDate,
            Integer rating,
            String uuid,
            String nickname,
            String text
    ) {
        this.reviewCode = reviewCode;
        this.productCode = productCode;
        this.options = options;
        this.creationDate = creationDate;
        this.rating = rating;
        this.uuid = uuid;
        this.nickname = nickname;
        this.text = text;
    }
}
