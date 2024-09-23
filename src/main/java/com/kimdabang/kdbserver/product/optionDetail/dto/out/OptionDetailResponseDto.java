package com.kimdabang.kdbserver.product.optionDetail.dto.out;

import com.kimdabang.kdbserver.product.optionDetail.vo.OptionDetailResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OptionDetailResponseDto {

    private Boolean state;
    private Long quantity;
    private Long minStock;
    private int variablePrice;

    public OptionDetailResponseVo toVo() {
        return OptionDetailResponseVo.builder()
                .state(state)
                .quantity(quantity)
                .minStock(minStock)
                .variablePrice(variablePrice)
                .build();
    }

}
