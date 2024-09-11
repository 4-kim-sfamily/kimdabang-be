package com.kimdabang.kdbserver.season.mediaList.application;

import com.kimdabang.kdbserver.season.mediaList.dto.in.SeasonMediaListRequestDto;
import com.kimdabang.kdbserver.season.mediaList.dto.out.SeasonMediaListResponseDto;

import java.util.List;

public interface SeasonMediaListService {

    void addSeasonMediaList(SeasonMediaListRequestDto seasonMediaListRequestDto);
    void updateSeasonMediaList(SeasonMediaListRequestDto seasonMediaListRequestDto);
    void deleteSeasonMediaList(Long id);
    SeasonMediaListResponseDto getOneSeasonMediaList(Long id);
    List<SeasonMediaListResponseDto> getAllSeasonMediaList();
}
