package com.kimdabang.kdbserver.product.detail.domain;

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
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("상품 코드")
    @Column(nullable = false, length = 50)
    private String productCode;

    @Lob  // 이 어노테이션을 사용하여 긴 문자열을 저장
    @Column(columnDefinition = "TEXT")  // MySQL에서 TEXT 타입을 명시
    private String content;

    public void updateContent(String content) {
        this.content = content;
    }
}
