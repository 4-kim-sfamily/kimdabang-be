package com.kimdabang.kdbserver.season.season.application;

import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.season.season.domain.Season;
import com.kimdabang.kdbserver.season.season.dto.in.SeasonAddRequestDto;
import com.kimdabang.kdbserver.season.season.dto.in.SeasonUpdateRequestDto;
import com.kimdabang.kdbserver.season.season.dto.out.SeasonResponseDto;
import com.kimdabang.kdbserver.season.season.infrastructure.SeasonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.SEASON_NOT_FOUND;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepository seasonRepository;

    @Override
    @Transactional
    public void addSeason(SeasonAddRequestDto seasonAddRequestDto) {
        seasonRepository.save(seasonAddRequestDto.toEntity());
    }

    @Override
    @Transactional
    public void updateSeason(SeasonUpdateRequestDto seasonUpdateRequestDto) {
        seasonRepository.findById(seasonUpdateRequestDto.getId())
                .orElseThrow(() -> new CustomException(SEASON_NOT_FOUND));

        seasonRepository.save(seasonUpdateRequestDto.toEntity());
    }

    @Override
    @Transactional
    public void deleteSeason(Long id) {
        Season deleteSeason = seasonRepository.findById(id)
                .orElseThrow(() -> new CustomException(SEASON_NOT_FOUND));

        seasonRepository.delete(deleteSeason);
    }

    @Override
    public SeasonResponseDto getOneSeason(Long id) {
        Season getSeason = seasonRepository.findById(id)
                .orElseThrow(() -> new CustomException(SEASON_NOT_FOUND));
        return SeasonResponseDto.builder()
                .id(getSeason.getId())
                .name(getSeason.getName())
                .description(getSeason.getDescription())
                .startDate(getSeason.getStartDate())
                .endDate(getSeason.getEndDate())
                .discount(getSeason.getDiscount())
                .build();
    }

    @Override
    public List<SeasonResponseDto> getAllSeason() {
        List<Season> seasons = seasonRepository.findAll();
        return seasons.stream()
                .map(season -> SeasonResponseDto.builder()
                        .id(season.getId())
                        .name(season.getName())
                        .description(season.getDescription())
                        .startDate(season.getStartDate())
                        .endDate(season.getEndDate())
                        .discount(season.getDiscount())
                        .build())
                .toList();
    }
}
