package com.kimdabang.kdbserver.starbucksCard.card.application;

import com.kimdabang.kdbserver.season.season.dto.out.SeasonResponseDto;
import com.kimdabang.kdbserver.starbucksCard.card.dto.in.StarbucksCardAddRequestDto;
import com.kimdabang.kdbserver.starbucksCard.card.dto.in.StarbucksCardUpdateRequestDto;

import java.util.List;

public interface StarbucksCardService {
    void addStarbucksCard(StarbucksCardAddRequestDto starbucksCardAddRequestDto);
    void updateStarbucksCard(StarbucksCardUpdateRequestDto starbucksCardUpdateRequestDto);
    void deleteStarbucksCard(Long id);
    SeasonResponseDto getOneStarbucksCard(Long id);
    List<SeasonResponseDto> getAllStarbucksCard();
}
