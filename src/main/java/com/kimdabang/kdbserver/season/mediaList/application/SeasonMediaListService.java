package com.kimdabang.kdbserver.season.mediaList.application;

import com.kimdabang.kdbserver.season.mediaList.dto.in.SeasonMediaListAddRequestDto;
import com.kimdabang.kdbserver.season.mediaList.dto.in.SeasonMediaListUpdateRequestDto;
import com.kimdabang.kdbserver.season.mediaList.dto.out.SeasonMediaListResponseDto;

import java.util.List;

public interface SeasonMediaListService {

    void addSeasonMediaList(SeasonMediaListAddRequestDto seasonMediaListAddRequestDto);
    void updateSeasonMediaList(SeasonMediaListUpdateRequestDto seasonMediaListUpdateRequestDto);
    void deleteSeasonMediaList(Long id);
    SeasonMediaListResponseDto getOneSeasonMediaList(Long id);
    List<SeasonMediaListResponseDto> getAllSeasonMediaList();
}
