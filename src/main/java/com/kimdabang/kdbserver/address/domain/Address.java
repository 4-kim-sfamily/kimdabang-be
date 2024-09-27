package com.kimdabang.kdbserver.address.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity
@NoArgsConstructor
@DynamicUpdate  //특정 칼럼만 update하기 위해 사용
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

    @Comment("전화 번호")
    @Column(nullable = false, length = 100)
    private String phone;

    @Comment("유저 uuid")
    @Column(nullable = false, length = 100)
    private String userUuid;

    @Builder
    public Address(
            Long id,
            String address,
            Boolean isDefault,
            String addressName,
            String phone,
            String userUuid

    ) {
        this.id = id;
        this.address = address;
        this.isDefault = isDefault;
        this.addressName = addressName;
        this.phone = phone;
        this.userUuid = userUuid;
    }
    public void updateIsDefault(Boolean isDefault)
    {
        this.isDefault = isDefault;
    }
}
