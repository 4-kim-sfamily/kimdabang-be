package com.kimdabang.kdbserver.product.optionDetail.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionDetailResponseVo {

    private Boolean state;
    private Long quantity;
    private Long minStock;
    private int variablePrice;

}
