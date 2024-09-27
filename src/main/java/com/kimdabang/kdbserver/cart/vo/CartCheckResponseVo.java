package com.kimdabang.kdbserver.cart.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartCheckResponseVo {

    private boolean isChecked;
    private Integer amount;

}
