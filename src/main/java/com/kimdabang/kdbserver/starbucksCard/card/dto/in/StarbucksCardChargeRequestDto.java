package com.kimdabang.kdbserver.starbucksCard.card.dto.in;

import com.kimdabang.kdbserver.starbucksCard.card.domain.StarbucksCard;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StarbucksCardChargeRequestDto {
    private Long id;
    private int charge;

    public StarbucksCard toEntity(StarbucksCard starbucksCard) {
        return StarbucksCard.builder()
                .id(id)
                .cardNumber(starbucksCard.getCardNumber())
                .lastUsedDate(LocalDateTime.now())
                .balance(starbucksCard.getBalance()+charge)
                .cardName(starbucksCard.getCardName())
                .build();
    }

    @Builder
    public StarbucksCardChargeRequestDto(Long id, int charge) {
        this.id = id;
        this.charge = charge;
    }
}
