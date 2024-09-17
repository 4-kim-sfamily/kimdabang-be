package com.kimdabang.kdbserver.cart.dto.out;

import com.kimdabang.kdbserver.cart.vo.CartResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CartResponseDto {

    private String productCode;
    private Integer amount;
    private Long productOptionId;

    public CartResponseVo toVo() {
        return CartResponseVo.builder()
                .productCode(productCode)
                .amount(amount)
                .productOptionId(productOptionId)
                .build();
    }

    @Builder
    public CartResponseDto(String productCode, Integer amount, Long productOptionId) {

        this.productCode = productCode;
        this.amount = amount;
        this.productOptionId = productOptionId;
    }
}
