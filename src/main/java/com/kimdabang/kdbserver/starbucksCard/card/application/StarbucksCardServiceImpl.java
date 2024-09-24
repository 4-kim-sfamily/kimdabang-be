package com.kimdabang.kdbserver.starbucksCard.card.application;

import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.starbucksCard.card.domain.StarbucksCard;
import com.kimdabang.kdbserver.starbucksCard.card.dto.in.StarbucksCardAddRequestDto;
import com.kimdabang.kdbserver.starbucksCard.card.dto.in.StarbucksCardUpdateRequestDto;
import com.kimdabang.kdbserver.starbucksCard.card.dto.out.StarbucksCardResponseDto;
import com.kimdabang.kdbserver.starbucksCard.card.infrastructure.StarbucksCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.*;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StarbucksCardServiceImpl implements StarbucksCardService {

    private final StarbucksCardRepository starbucksCardRepository;

    @Override
    @Transactional
    public void addStarbucksCard(StarbucksCardAddRequestDto starbucksCardAddRequestDto) {
        starbucksCardRepository.save(starbucksCardAddRequestDto.toEntity());
    }

    @Override
    @Transactional
    public void updateStarbucksCard(StarbucksCardUpdateRequestDto starbucksCardUpdateRequestDto) {
        starbucksCardRepository.findById(starbucksCardUpdateRequestDto.getId())
                .orElseThrow(() -> new CustomException(STARBUCKSCARD_NOT_FOUND));
        starbucksCardRepository.save(starbucksCardUpdateRequestDto.toEntity());
    }

    @Override
    @Transactional
    public void deleteStarbucksCard(Long id) {
        StarbucksCard deleteStarbucksCard = starbucksCardRepository.findById(id)
                .orElseThrow(() -> new CustomException(STARBUCKSCARD_NOT_FOUND));
        starbucksCardRepository.delete(deleteStarbucksCard);
    }

    @Override
    public StarbucksCardResponseDto getOneStarbucksCard(Long id) {
        StarbucksCard starbucksCard = starbucksCardRepository.findById(id)
                .orElseThrow(() -> new CustomException(STARBUCKSCARD_NOT_FOUND));
        return StarbucksCardResponseDto.builder()
                .id(starbucksCard.getId())
                .cardNumber(starbucksCard.getCardNumber())
                .lastUsedDate(starbucksCard.getLastUsedDate())
                .balance(starbucksCard.getBalance())
                .cardName(starbucksCard.getCardName())
                .build();
    }

    @Override
    public List<StarbucksCardResponseDto> getAllStarbucksCard() {
        List<StarbucksCard> starbucksCards = starbucksCardRepository.findAll();
        return starbucksCards.stream()
                .map(starbucksCard -> StarbucksCardResponseDto.builder()
                        .id(starbucksCard.getId())
                        .cardNumber(starbucksCard.getCardNumber())
                        .lastUsedDate(starbucksCard.getLastUsedDate())
                        .balance(starbucksCard.getBalance())
                        .cardName(starbucksCard.getCardName())
                        .build())
                .toList();
    }
}
