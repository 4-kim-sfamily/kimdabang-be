package com.kimdabang.kdbserver.product.option.dto.out;

import com.kimdabang.kdbserver.product.option.domain.Option;
import com.kimdabang.kdbserver.product.option.vo.OptionResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@ToString
public class OptionResponseDto {

    private Long optionsId;
    private int depth;
    private String optionValue;
    private List<OptionResponseDto> children = new ArrayList<>();

}
