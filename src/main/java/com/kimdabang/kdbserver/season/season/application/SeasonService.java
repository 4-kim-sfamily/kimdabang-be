package com.kimdabang.kdbserver.season.season.application;

import com.kimdabang.kdbserver.season.season.dto.in.SeasonRequestDto;
import com.kimdabang.kdbserver.season.season.dto.out.SeasonResponseDto;

import java.util.List;

public interface SeasonService {

    void addSeason(SeasonRequestDto seasonRequestDto);
    void updateSeason(SeasonRequestDto seasonRequestDto);
    void deleteSeason(Long id);
    SeasonResponseDto getOneSeason(Long id);
    List<SeasonResponseDto> getAllSeason();
}
