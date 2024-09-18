package com.kimdabang.kdbserver.product.media.domain;

import com.kimdabang.kdbserver.common.entity.BaseEntity;
import com.kimdabang.kdbserver.product.product.domain.Product;
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
@Table(name = "product_media")
public class ProductMedia extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Comment("상품 pk")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;
    @Comment("상품 코드")
    @Column(nullable = false)
    private String productCode;
    @Comment("미디어 경로 URL")
    @Column(nullable = false)
    private String mediaURL;
    @Comment("미디어 이름")
    @Column(nullable = false)
    private String mediaName;
    @Comment("미디어 타입")
    @Column(nullable = false)
    private String mediaType;

}
