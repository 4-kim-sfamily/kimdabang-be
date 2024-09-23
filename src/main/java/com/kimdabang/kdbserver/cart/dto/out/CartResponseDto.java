package com.kimdabang.kdbserver.cart.dto.out;

import com.kimdabang.kdbserver.cart.vo.CartResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CartResponseDto {

    private String productCode;
    private Integer amount;
    private Long productOptionId;
    private String carving;

    public CartResponseVo toVo() {
        return CartResponseVo.builder()
                .productCode(productCode)
                .amount(amount)
                .productOptionId(productOptionId)
                .carving(carving)
                .build();
    }

    @Builder
    public CartResponseDto(String productCode, Integer amount, Long productOptionId, String carving) {

        this.productCode = productCode;
        this.amount = amount;
        this.productOptionId = productOptionId;
        this.carving = carving;
    }
}
