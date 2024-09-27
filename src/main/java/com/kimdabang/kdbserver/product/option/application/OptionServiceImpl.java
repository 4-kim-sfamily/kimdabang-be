package com.kimdabang.kdbserver.product.option.application;

import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.product.option.domain.Option;
import com.kimdabang.kdbserver.product.option.dto.out.OptionResponseDto;
import com.kimdabang.kdbserver.product.option.infrastructure.OptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.PARENTSOPTION_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {

    private final OptionRepository optionRepository;

    public List<OptionResponseDto> getOptionListByProductCode(String productCode, Long parentId) {
        List<Option> options = List.of();
        if (parentId == null) {
            options = optionRepository.findAllByProductCodeAndDepth(productCode, 1);
        } else {
            Option parentOption = optionRepository.findById(parentId)
                    .orElse(null);
            if (parentOption != null) {
                options = optionRepository.findAllByProductCodeAndParentOptionsId(productCode, parentOption);
                }
        }

        List<OptionResponseDto> result = new ArrayList<>();
        for (Option o : options) {
            List<OptionResponseDto> children = getOptionListByProductCode(productCode, o.getId());

            OptionResponseDto dto = OptionResponseDto.builder()
                    .optionsId(o.getId())
                    .depth(o.getDepth())
                    .optionValue(o.getOptionValue())
                    .children(children)
                    .build();

            result.add(dto);
        }
        return result;
    }

    @Override
    public String getFamilyOption(String productCode, Long optionId) {

        Option option = optionRepository.findByProductCodeAndId(productCode, optionId)
                .orElseThrow(() -> new CustomException(PARENTSOPTION_NOT_FOUND));

        StringBuilder familyOption = new StringBuilder(option.getOptionValue());
        Long parentId = option.getParentOptionsId() != null ? option.getParentOptionsId().getId() : null;

        while (parentId != null) {
            Option parentOption = optionRepository.findByProductCodeAndId(productCode, parentId)
                    .orElseThrow(() -> new CustomException(PARENTSOPTION_NOT_FOUND));
            familyOption.insert(0, parentOption.getOptionValue() + ", ");
            parentId = parentOption.getParentOptionsId() != null ? parentOption.getParentOptionsId().getId() : null;
        }

        return familyOption.toString();
    }



}
