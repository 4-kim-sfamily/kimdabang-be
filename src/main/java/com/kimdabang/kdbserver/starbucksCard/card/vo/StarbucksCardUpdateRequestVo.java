package com.kimdabang.kdbserver.starbucksCard.card.vo;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class StarbucksCardUpdateRequestVo {
    private Long id;
    private String cardNumber;
    private LocalDateTime lastUsedDate;
    private int balance;
    private String cardName;
}
