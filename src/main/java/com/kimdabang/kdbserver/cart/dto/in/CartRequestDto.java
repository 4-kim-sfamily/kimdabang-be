package com.kimdabang.kdbserver.cart.dto.in;

import com.kimdabang.kdbserver.cart.domain.Cart;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class CartRequestDto {

    private String productCode;
    private String accessToken;
    private Integer amount;
    private Long productOptionId;
    private String carving;

    public Cart toCartEntity(String userUuid, Boolean isChecked) {
        return Cart.builder()
                .userUuid(userUuid)
                .productCode(productCode)
                .amount(amount)
                .productOptionId(productOptionId)
                .carving(carving)
                .checkBox(true)
                .isChecked(isChecked)
                .build();
    }


    @Builder
    public CartRequestDto(String productCode, String accessToken, Integer amount, Long productOptionId, String carving) {

        this.productCode = productCode;
        this.accessToken = accessToken;
        this.amount = amount;
        this.productOptionId = productOptionId;
        this.carving = carving;

    }
}
