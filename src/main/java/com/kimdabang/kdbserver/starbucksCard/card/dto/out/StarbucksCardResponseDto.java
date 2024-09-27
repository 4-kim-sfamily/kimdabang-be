package com.kimdabang.kdbserver.starbucksCard.card.dto.out;

import com.kimdabang.kdbserver.starbucksCard.card.vo.StarbucksCardResponseVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StarbucksCardResponseDto {
    private Long id;
    private String cardNumber;
    private LocalDateTime lastUsedDate;
    private int balance;
    private String cardName;

    public StarbucksCardResponseVo toResponseVo() {
        return StarbucksCardResponseVo.builder()
                .id(id)
                .cardNumber(cardNumber)
                .lastUsedDate(lastUsedDate)
                .balance(balance)
                .cardName(cardName)
                .build();
    }

    @Builder
    public StarbucksCardResponseDto(Long id, String cardNumber, LocalDateTime lastUsedDate, int balance, String cardName) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.lastUsedDate = lastUsedDate;
        this.balance = balance;
        this.cardName = cardName;
    }
}
