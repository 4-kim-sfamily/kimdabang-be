package com.kimdabang.kdbserver.product.option.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionResponseVo {

    private Long optionId;
    private int depth;
    private String optionValue;
    private List<OptionResponseVo> optionList;

}
