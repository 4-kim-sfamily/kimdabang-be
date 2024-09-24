package com.kimdabang.kdbserver.starbucksCard.card.dto.in;

import com.kimdabang.kdbserver.starbucksCard.card.domain.StarbucksCard;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StarbucksCardUpdateRequestDto {
    private Long id;
    private String cardNumber;
    private LocalDateTime lastUsedDate;
    private int balance;
    private String cardName;

    public StarbucksCard toEntity() {
        return StarbucksCard.builder()
                .id(id)
                .cardNumber(cardNumber)
                .lastUsedDate(lastUsedDate)
                .balance(balance)
                .cardName(cardName)
                .build();
    }

    @Builder
    public StarbucksCardUpdateRequestDto(Long id, String cardNumber, LocalDateTime lastUsedDate, int balance, String cardName) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.lastUsedDate = lastUsedDate;
        this.balance = balance;
        this.cardName = cardName;
    }
}
