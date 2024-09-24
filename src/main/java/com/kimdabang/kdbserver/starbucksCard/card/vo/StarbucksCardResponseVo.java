package com.kimdabang.kdbserver.starbucksCard.card.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StarbucksCardResponseVo {
    private Long id;
    private String cardNumber;
    private LocalDateTime lastUsedDate;
    private int balance;
    private String cardName;
}
