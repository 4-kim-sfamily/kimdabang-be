package com.kimdabang.kdbserver.product.optionDetail.application;

import com.kimdabang.kdbserver.product.optionDetail.vo.OptionDetailResponseVo;

public interface OptionDetailService {

    OptionDetailResponseVo getOptionDetail(String productCode, Long optionId);

}
