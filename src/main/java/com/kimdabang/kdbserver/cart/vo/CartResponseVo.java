package com.kimdabang.kdbserver.cart.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseVo {

    private String productCode;
    private Integer amount;
    private Long productOptionId;

}
