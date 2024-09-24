package com.kimdabang.kdbserver.starbucksCard.card.domain;

import com.kimdabang.kdbserver.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@Entity
@ToString
@NoArgsConstructor
public class StarbucksCard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("카드 번호")
    @Column(nullable = false)
    private String cardNumber;

    @Comment("마지막 사용 날짜")
    @Column(nullable = false)
    private LocalDateTime lastUsedDate;

    @Comment("잔액")
    @Column(nullable = false)
    private int balance;

    @Comment("카드 이름")
    private String cardName;

    @Builder
    public StarbucksCard(Long id, String cardNumber, LocalDateTime lastUsedDate, int balance, String cardName) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.lastUsedDate = lastUsedDate;
        this.balance = balance;
        this.cardName = cardName;
    }
}
