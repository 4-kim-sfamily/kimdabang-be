package com.kimdabang.kdbserver.address.domain;

import com.kimdabang.kdbserver.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("배송지")
    @Column(nullable = false, length = 100)
    private String address;

    @Comment("기본 배송지 여부")
    @Column(nullable = false)
    private Boolean isDefault;

    @Comment("주소 별칭")
    @Column(nullable = false, length = 100)
    private String addressName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Address(
            Long id,
            String address,
            Boolean isDefault,
            String addressName,
            User user

    ) {
        this.id = id;
        this.address = address;
        this.isDefault = isDefault;
        this.addressName = addressName;
        this.user = user;
    }

}
