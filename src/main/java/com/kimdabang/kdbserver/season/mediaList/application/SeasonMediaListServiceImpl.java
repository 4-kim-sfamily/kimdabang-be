package com.kimdabang.kdbserver.season.mediaList.application;

import com.kimdabang.kdbserver.season.mediaList.domain.SeasonMediaList;
import com.kimdabang.kdbserver.season.mediaList.dto.in.SeasonMediaListAddRequestDto;
import com.kimdabang.kdbserver.season.mediaList.dto.in.SeasonMediaListUpdateRequestDto;
import com.kimdabang.kdbserver.season.mediaList.dto.out.SeasonMediaListResponseDto;
import com.kimdabang.kdbserver.season.mediaList.infrastructure.SeasonMediaListRepository;
import com.kimdabang.kdbserver.season.season.domain.Season;
import com.kimdabang.kdbserver.season.season.infrastructure.SeasonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SeasonMediaListServiceImpl implements SeasonMediaListService {

    private final SeasonMediaListRepository seasonMediaListRepository;
    private final SeasonRepository seasonRepository;

    @Override
    @Transactional
    public void addSeasonMediaList(SeasonMediaListAddRequestDto seasonMediaListRequestDto) {
        Season season = seasonRepository.findById(seasonMediaListRequestDto.getSeasonId())
                .orElseThrow(() -> new IllegalArgumentException("해당 시즌이 존재하지 않습니다."));

        seasonMediaListRepository.save(seasonMediaListRequestDto.toEntity(season));
    }

    @Override
    @Transactional
    public void updateSeasonMediaList(SeasonMediaListUpdateRequestDto seasonMediaListUpdateRequestDto) {
        SeasonMediaList seasonMediaList = seasonMediaListRepository.findById(seasonMediaListUpdateRequestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 시즌의 미디어가 존재하지 않습니다."));
        seasonMediaListRepository.save(seasonMediaListUpdateRequestDto.toEntity(seasonMediaList));
    }

    @Override
    @Transactional
    public void deleteSeasonMediaList(Long id) {
        SeasonMediaList deleteSeasonMediaList = seasonMediaListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 시즌의 미디어가 존재하지 않습니다."));
        seasonMediaListRepository.delete(deleteSeasonMediaList);
    }

    @Override
    public SeasonMediaListResponseDto getOneSeasonMediaList(Long id) {
        // TODO: 9/13/24  
        return null;
    }

    @Override
    public List<SeasonMediaListResponseDto> getAllSeasonMediaList() {
        // TODO: 9/13/24  
        return null;
    }
}
