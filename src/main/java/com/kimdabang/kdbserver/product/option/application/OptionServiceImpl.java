package com.kimdabang.kdbserver.product.option.application;

import com.kimdabang.kdbserver.product.option.domain.Option;
import com.kimdabang.kdbserver.product.option.dto.out.OptionResponseDto;
import com.kimdabang.kdbserver.product.option.infrastructure.OptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
                    .optionValue(o.getOptionValue())
                    .children(children)
                    .build();

            result.add(dto);
        }
        return result;
    }


}
