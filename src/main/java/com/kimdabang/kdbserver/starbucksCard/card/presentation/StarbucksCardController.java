package com.kimdabang.kdbserver.starbucksCard.card.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.starbucksCard.card.application.StarbucksCardService;
import com.kimdabang.kdbserver.starbucksCard.card.dto.in.StarbucksCardAddRequestDto;
import com.kimdabang.kdbserver.starbucksCard.card.vo.StarbucksCardAddRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/starbucks-card")
public class StarbucksCardController {

    private final StarbucksCardService starbucksCardService;

    @PostMapping
    public CommonResponseEntity<Void> createStarbucksCard(@RequestBody StarbucksCardAddRequestVo starbucksCardAddRequestVo) {
        log.info("starbucksCardAddRequestVo = {}", starbucksCardAddRequestVo);
        StarbucksCardAddRequestDto starbucksCardAddRequestDto = StarbucksCardAddRequestDto.builder()
                .cardNumber(starbucksCardAddRequestVo.getCardNumber())
                .lastUsedDate(starbucksCardAddRequestVo.getLastUsedDate())
                .balance(starbucksCardAddRequestVo.getBalance())
                .cardName(starbucksCardAddRequestVo.getCardName())
                .build();
        starbucksCardService.addStarbucksCard(starbucksCardAddRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "스타벅스카드 등록 성공",
                null
        );
    }
}
