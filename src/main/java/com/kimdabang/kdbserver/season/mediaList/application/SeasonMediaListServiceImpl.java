package com.kimdabang.kdbserver.season.mediaList.application;

import com.kimdabang.kdbserver.common.exception.CustomException;
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

import static com.kimdabang.kdbserver.common.exception.ErrorCode.SEASONMEDIA_NOT_FOUND;
import static com.kimdabang.kdbserver.common.exception.ErrorCode.SEASON_NOT_FOUND;

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
                .orElseThrow(() -> new CustomException(SEASON_NOT_FOUND));

        seasonMediaListRepository.save(seasonMediaListRequestDto.toEntity(season));
    }

    @Override
    @Transactional
    public void updateSeasonMediaList(SeasonMediaListUpdateRequestDto seasonMediaListUpdateRequestDto) {
        SeasonMediaList seasonMediaList = seasonMediaListRepository.findById(seasonMediaListUpdateRequestDto.getId())
                .orElseThrow(() -> new CustomException(SEASONMEDIA_NOT_FOUND));
        seasonMediaListRepository.save(seasonMediaListUpdateRequestDto.toEntity(seasonMediaList));
    }

    @Override
    @Transactional
    public void deleteSeasonMediaList(Long id) {
        SeasonMediaList deleteSeasonMediaList = seasonMediaListRepository.findById(id)
                .orElseThrow(() -> new CustomException(SEASONMEDIA_NOT_FOUND));
        seasonMediaListRepository.delete(deleteSeasonMediaList);
    }

    @Override
    public SeasonMediaListResponseDto getOneSeasonMediaList(Long id) {
        SeasonMediaList getSeasonMediaList = seasonMediaListRepository.findById(id)
                .orElseThrow(() -> new CustomException(SEASONMEDIA_NOT_FOUND));
        return SeasonMediaListResponseDto.builder()
                .id(getSeasonMediaList.getId())
                .seasonId(getSeasonMediaList.getSeason().getId())
                .mediaURL(getSeasonMediaList.getMediaURL())
                .imageName(getSeasonMediaList.getImageName())
                .mediaType(getSeasonMediaList.getMediaType())
                .build();
    }

    @Override
    public List<SeasonMediaListResponseDto> getAllSeasonMediaList() {
        List<SeasonMediaList> seasonMediaLists = seasonMediaListRepository.findAll();
        return seasonMediaLists.stream()
                .map(seasonMediaList -> SeasonMediaListResponseDto.builder()
                        .id(seasonMediaList.getId())
                        .seasonId(seasonMediaList.getSeason().getId())
                        .mediaURL(seasonMediaList.getMediaURL())
                        .imageName(seasonMediaList.getImageName())
                        .mediaType(seasonMediaList.getMediaType())
                        .build())
                .toList();
    }
}
