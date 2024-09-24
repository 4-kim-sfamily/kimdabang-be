package com.kimdabang.kdbserver.starbucksCard.card.application;

import com.kimdabang.kdbserver.season.season.dto.out.SeasonResponseDto;
import com.kimdabang.kdbserver.starbucksCard.card.dto.in.StarbucksCardAddRequestDto;
import com.kimdabang.kdbserver.starbucksCard.card.dto.in.StarbucksCardUpdateRequestDto;
import com.kimdabang.kdbserver.starbucksCard.card.infrastructure.StarbucksCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        // TODO: 9/23/24
    }

    @Override
    @Transactional
    public void deleteStarbucksCard(Long id) {
        // TODO: 9/23/24
    }

    @Override
    public SeasonResponseDto getOneStarbucksCard(Long id) {
        // TODO: 9/23/24
        return null;
    }

    @Override
    public List<SeasonResponseDto> getAllStarbucksCard() {
        // TODO: 9/23/24
        return null;
    }
}
