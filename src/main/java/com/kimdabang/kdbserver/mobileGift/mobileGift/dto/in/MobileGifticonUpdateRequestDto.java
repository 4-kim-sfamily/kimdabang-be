package com.kimdabang.kdbserver.mobileGift.mobileGift.dto.in;

import com.kimdabang.kdbserver.mobileGift.mobileGift.domain.MobileGifticon;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MobileGifticonUpdateRequestDto {

    private Long id;
    private String number;
    private int price;

    public MobileGifticon toEntity() {
        return MobileGifticon.builder()
                .id(id)
                .number(number)
                .price(price)
                .build();
    }

    @Builder
    public MobileGifticonUpdateRequestDto(Long id, String number, int price) {
        this.id = id;
        this.number = number;
        this.price = price;
    }
}
