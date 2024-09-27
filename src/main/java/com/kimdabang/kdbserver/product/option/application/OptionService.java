package com.kimdabang.kdbserver.product.option.application;

import com.kimdabang.kdbserver.product.option.dto.out.OptionResponseDto;

import java.util.List;

public interface OptionService {

    List<OptionResponseDto> getOptionListByProductCode(String productCode, Long parentOptionsId);
    String getFamilyOption(String productCode, Long optionId);

}
