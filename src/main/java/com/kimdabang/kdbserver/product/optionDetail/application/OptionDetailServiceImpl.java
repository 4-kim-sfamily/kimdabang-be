package com.kimdabang.kdbserver.product.optionDetail.application;

import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.product.optionDetail.domain.OptionDetail;
import com.kimdabang.kdbserver.product.optionDetail.dto.out.OptionDetailResponseDto;
import com.kimdabang.kdbserver.product.optionDetail.infrastructure.OptionDetailRepository;
import com.kimdabang.kdbserver.product.optionDetail.vo.OptionDetailResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.OPTIONDETAIL_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class OptionDetailServiceImpl implements OptionDetailService {

    private final OptionDetailRepository optionDetailRepository;

    @Override
    public OptionDetailResponseVo getOptionDetail(String productCode, Long optionId) {

        OptionDetail optionDetail = optionDetailRepository.findByProductCodeAndOptionId(productCode, optionId)
                .orElseThrow(() -> new CustomException(OPTIONDETAIL_NOT_FOUND));

        return OptionDetailResponseDto.builder()
                .state(optionDetail.getState())
                .quantity(optionDetail.getQuantity())
                .minStock(optionDetail.getMinStock())
                .variablePrice(optionDetail.getVariablePrice())
                .build().toVo();
    }
}
