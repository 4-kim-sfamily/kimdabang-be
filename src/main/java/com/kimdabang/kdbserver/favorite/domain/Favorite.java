package com.kimdabang.kdbserver.favorite.domain;

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
@Table(name = "favorite")
public class Favorite extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("유저 UUID")
    @Column(nullable = false, unique = false, length = 100)
    private String userUuid;

    @Comment("상품 코드")
    @Column(nullable = false)
    private String productCode;

    @Comment("좋아요 취소 여부")
    @Column(nullable = false)
    private Boolean isCanceled;


    public void change() {
        this.isCanceled = !this.isCanceled;
    }

    public boolean isCanceled() {
        return this.isCanceled;
    }
}
