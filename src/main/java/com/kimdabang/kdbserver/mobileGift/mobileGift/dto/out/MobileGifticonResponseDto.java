package com.kimdabang.kdbserver.mobileGift.mobileGift.dto.out;

import com.kimdabang.kdbserver.mobileGift.mobileGift.vo.MobileGifticonResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MobileGifticonResponseDto {
    private Long id;
    private String number;
    private int price;

    public MobileGifticonResponseVo toResponseVo() {
        return MobileGifticonResponseVo.builder()
                .id(id)
                .number(number)
                .price(price)
                .build();
    }

    @Builder
    public MobileGifticonResponseDto(Long id, String number, int price) {
        this.id = id;
        this.number = number;
        this.price = price;
    }
}
