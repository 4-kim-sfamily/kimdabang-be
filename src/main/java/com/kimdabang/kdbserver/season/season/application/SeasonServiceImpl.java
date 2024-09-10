package com.kimdabang.kdbserver.season.season.application;

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

    }

    @Override
    @Transactional
    public void deleteSeason(Long id) {

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
