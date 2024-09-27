package com.kimdabang.kdbserver.cart.vo;

import lombok.Getter;

@Getter
public class CartRequestVo {

    private Integer amount;
    private Long productOptionId;
    private String carving;

}
