package com.kimdabang.kdbserver.mobileGift.mobileGift.dto.in;

import com.kimdabang.kdbserver.mobileGift.mobileGift.domain.MobileGifticon;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MobileGifticonAddRequestDto {

    private String number;
    private int price;

    public MobileGifticon toEntity() {
        return MobileGifticon.builder()
                .number(number)
                .price(price)
                .build();
    }

    @Builder
    public MobileGifticonAddRequestDto(String number, int price) {
        this.number = number;
        this.price = price;
    }
}
