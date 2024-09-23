package com.kimdabang.kdbserver.cart.dto.out;

import com.kimdabang.kdbserver.cart.vo.CartCheckBoxResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CartCheckBoxResponseDto {

    private boolean checkBox;

    public CartCheckBoxResponseVo toVo() {
        return CartCheckBoxResponseVo.builder()
                .checkBox(checkBox)
                .build();
    }

    @Builder
    public CartCheckBoxResponseDto(boolean checkBox) {

        this.checkBox = checkBox;

    }
}
