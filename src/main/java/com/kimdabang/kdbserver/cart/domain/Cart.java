package com.kimdabang.kdbserver.cart.domain;

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
@Table(name = "cart")
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("유저 UUID")
    @Column(nullable = false, unique = false, length = 100)
    private String userUuid;

    @Comment("상품 코드")
    @Column(nullable = false)
    private String productCode;

//    private Boolean isMember;
//  todo: 비회원 장바구니 관리

    @Comment("등록 수량")
    @Column(nullable = false)
    private Integer amount;

    @Comment("등록 일자")
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Comment("수정 일자")
    @Column(nullable = false)
    private Date updatedAt;

    @Comment("상품 옵션 ID")
    @Column(nullable = false)
    private Long productOptionId;

//    private String deviceKey;
//    todo: 비회원 장바구니 관리를 위한 Key
    @Comment("장바구니 등록 여부")
    @Column(nullable = false)
    private Boolean isChecked;

    @Comment("장바구니 체크박스")
    @Column(nullable = false)
    private Boolean checkBox;

    @Comment("상품 각인 데이터")
    @Column(nullable = true, length = 10)
    private String carving;

    @PrePersist
    protected void onCreate() {
        Date now = new Date();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    public void onAmount(Integer amount) {
        this.isChecked = true;
        this.amount = amount;
    }

    public void toZero() {
        this.isChecked = false;
        this.amount = 0;
    }

    public boolean isChecked() {return this.isChecked;}

    public void changeCheckBox() {this.checkBox = !this.checkBox;}
}
