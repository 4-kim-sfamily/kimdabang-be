package com.kimdabang.kdbserver.orders.orders.domain;

import com.kimdabang.kdbserver.commonEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@ToString
@NoArgsConstructor
public class Orders extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Comment("고객 아이디")
    @Column(nullable = false)
    private User user;

    @Comment("주문 번호")
    @Column(nullable = false, length = 100)
    private String orderNumber;

    @Comment("배송지 주소")
    @Column(nullable = false, length = 100)
    private Address address;

    @Comment("주문자 이름")
    @Column(nullable = false, length = 100)
    private String name;

    @Comment("주문자 전화번호")
    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Comment("주문 상태")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @Builder
    public Orders (
        Long id,
        User user,
        String orderNumber,
        String address,
        String name,
        String phoneNumber,
        OrderState orderState
    ) {
        this.id = id;
        this.user = user;
        this.orderNumber = orderNumber;
        this.address = address;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.orderState = orderState;
    }
}
