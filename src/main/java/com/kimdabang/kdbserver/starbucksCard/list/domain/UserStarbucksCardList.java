package com.kimdabang.kdbserver.starbucksCard.list.domain;

import com.kimdabang.kdbserver.common.entity.BaseEntity;
import com.kimdabang.kdbserver.starbucksCard.card.domain.StarbucksCard;
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
public class UserStarbucksCardList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("고객 uuid")
    @Column(nullable = false)
    private String uuid;

    @Comment("스타벅스카드 pk")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "starbucksCard_id")
    private StarbucksCard starbucksCard;

    @Builder
    public UserStarbucksCardList(Long id, String uuid, StarbucksCard starbucksCard) {
        this.id = id;
        this.uuid = uuid;
        this.starbucksCard = starbucksCard;
    }
}
