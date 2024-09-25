package com.kimdabang.kdbserver.mobileGift.userEnrollMG.domain;

import com.kimdabang.kdbserver.common.entity.BaseEntity;
import com.kimdabang.kdbserver.mobileGift.mobileGift.domain.MobileGifticon;
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
public class UserEnrollMobileGifticon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("고객 uuid")
    @Column(nullable = false)
    private String uuid;

    @Comment("모바일상품권 pk")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mobileGifticon_id")
    private MobileGifticon mobileGifticon;

    @Builder
    public UserEnrollMobileGifticon(Long id, String uuid, MobileGifticon mobileGifticon) {
        this.id = id;
        this.uuid = uuid;
        this.mobileGifticon = mobileGifticon;
    }
}
