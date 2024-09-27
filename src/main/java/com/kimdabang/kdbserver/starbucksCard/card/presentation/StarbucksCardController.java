package com.kimdabang.kdbserver.starbucksCard.card.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.starbucksCard.card.application.StarbucksCardService;
import com.kimdabang.kdbserver.starbucksCard.card.dto.in.StarbucksCardAddRequestDto;
import com.kimdabang.kdbserver.starbucksCard.card.dto.in.StarbucksCardUpdateRequestDto;
import com.kimdabang.kdbserver.starbucksCard.card.dto.out.StarbucksCardResponseDto;
import com.kimdabang.kdbserver.starbucksCard.card.vo.StarbucksCardAddRequestVo;
import com.kimdabang.kdbserver.starbucksCard.card.vo.StarbucksCardResponseVo;
import com.kimdabang.kdbserver.starbucksCard.card.vo.StarbucksCardUpdateRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping
    public CommonResponseEntity<Void> updateStarbucksCard(@RequestBody StarbucksCardUpdateRequestVo starbucksCardUpdateRequestVo) {
        log.info("starbucksCardUpdateRequestVo = {}", starbucksCardUpdateRequestVo);
        StarbucksCardUpdateRequestDto starbucksCardUpdateRequestDto = StarbucksCardUpdateRequestDto.builder()
                .id(starbucksCardUpdateRequestVo.getId())
                .cardNumber(starbucksCardUpdateRequestVo.getCardNumber())
                .lastUsedDate(starbucksCardUpdateRequestVo.getLastUsedDate())
                .balance(starbucksCardUpdateRequestVo.getBalance())
                .cardName(starbucksCardUpdateRequestVo.getCardName())
                .build();
        starbucksCardService.updateStarbucksCard(starbucksCardUpdateRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "스타벅스카드 업데이트 성공",
                null
        );
    }

    @DeleteMapping
    public CommonResponseEntity<Void> deleteStarbucksCard(@RequestParam Long id) {
        log.info("starbucksCardId = {}", id);
        starbucksCardService.deleteStarbucksCard(id);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "스타벅스카드 삭제 성공",
                null
        );
    }

    @GetMapping
    public CommonResponseEntity<List<StarbucksCardResponseVo>> getAllStarbucksCard() {
        List<StarbucksCardResponseDto> starbucksCardResponseDtos = starbucksCardService.getAllStarbucksCard();
        List<StarbucksCardResponseVo> starbucksCardResponseVos = starbucksCardResponseDtos.stream()
                .map(StarbucksCardResponseDto::toResponseVo)
                .toList();
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "스타벅스카드 전체 조회 성공",
                starbucksCardResponseVos
        );
    }

    @GetMapping("/{id}")
    public CommonResponseEntity<StarbucksCardResponseVo> getOneStarbucksCard(@PathVariable Long id) {
        StarbucksCardResponseDto starbucksCardResponseDto = starbucksCardService.getOneStarbucksCard(id);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "스타벅스카드 조회 성공",
                starbucksCardResponseDto.toResponseVo()
        );
    }
}
