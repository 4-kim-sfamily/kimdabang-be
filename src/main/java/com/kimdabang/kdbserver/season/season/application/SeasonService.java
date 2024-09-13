package com.kimdabang.kdbserver.season.season.application;

import com.kimdabang.kdbserver.season.season.dto.in.SeasonAddRequestDto;
import com.kimdabang.kdbserver.season.season.dto.in.SeasonUpdateRequestDto;
import com.kimdabang.kdbserver.season.season.dto.out.SeasonResponseDto;

import java.util.List;

public interface SeasonService {

    void addSeason(SeasonAddRequestDto seasonAddRequestDto);
    void updateSeason(SeasonUpdateRequestDto seasonUpdateRequestDto);
    void deleteSeason(Long id);
    SeasonResponseDto getOneSeason(Long id);
    List<SeasonResponseDto> getAllSeason();
}
