package com.kimdabang.kdbserver.season.season.application;

import com.kimdabang.kdbserver.season.season.domain.Season;
import com.kimdabang.kdbserver.season.season.dto.in.SeasonRequestDto;
import com.kimdabang.kdbserver.season.season.dto.out.SeasonResponseDto;
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
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepository seasonRepository;

    @Override
    @Transactional
    public void addSeason(SeasonRequestDto seasonRequestDto) {
        seasonRepository.save(seasonRequestDto.toEntity());
    }

    @Override
    @Transactional
    public void updateSeason(SeasonRequestDto seasonRequestDto) {
        seasonRepository.findById(seasonRequestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 시즌이 존재하지 않습니다."));

        seasonRepository.save(seasonRequestDto.toEntity());
    }

    @Override
    @Transactional
    public void deleteSeason(Long id) {
        Season deleteSeason = seasonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 쿠폰이 존재하지 않습니다."));

        seasonRepository.delete(deleteSeason);
    }

    @Override
    public SeasonResponseDto getOneSeason(Long id) {
        return null;
    }

    @Override
    public List<SeasonResponseDto> getAllSeason() {
        return null;
    }
}
