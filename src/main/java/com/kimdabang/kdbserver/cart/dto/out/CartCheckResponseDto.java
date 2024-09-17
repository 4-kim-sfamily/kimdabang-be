package com.kimdabang.kdbserver.cart.dto.out;

import com.kimdabang.kdbserver.cart.vo.CartCheckResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CartCheckResponseDto {

    private boolean isChecked;
    private Integer amount;

    public CartCheckResponseVo toVo() {
        return CartCheckResponseVo.builder()
                .isChecked(isChecked)
                .amount(amount)
                .build();
    }

    @Builder
    public CartCheckResponseDto(boolean isChecked, Integer amount) {

        this.isChecked = isChecked;
        this.amount = amount;

    }
}
