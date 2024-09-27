package com.kimdabang.kdbserver.starbucksCard.card.dto.in;

import com.kimdabang.kdbserver.starbucksCard.card.domain.StarbucksCard;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StarbucksCardAddRequestDto {
    private String cardNumber;
    private LocalDateTime lastUsedDate;
    private int balance;
    private String cardName;

    public StarbucksCard toEntity() {
        return StarbucksCard.builder()
                .cardNumber(cardNumber)
                .lastUsedDate(lastUsedDate)
                .balance(balance)
                .cardName(cardName)
                .build();
    }

    @Builder
    public StarbucksCardAddRequestDto(String cardNumber, LocalDateTime lastUsedDate, int balance, String cardName) {
        this.cardNumber = cardNumber;
        this.lastUsedDate = lastUsedDate;
        this.balance = balance;
        this.cardName = cardName;
    }
}
