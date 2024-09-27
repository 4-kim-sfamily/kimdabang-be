package com.kimdabang.kdbserver.season.season.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.season.season.application.SeasonService;
import com.kimdabang.kdbserver.season.season.dto.in.SeasonAddRequestDto;
import com.kimdabang.kdbserver.season.season.dto.in.SeasonUpdateRequestDto;
import com.kimdabang.kdbserver.season.season.dto.out.SeasonResponseDto;
import com.kimdabang.kdbserver.season.season.vo.SeasonAddRequestVo;
import com.kimdabang.kdbserver.season.season.vo.SeasonResponseVo;
import com.kimdabang.kdbserver.season.season.vo.SeasonUpdateRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/season")
public class SeasonController {

    private final SeasonService seasonService;

    @PostMapping
    public CommonResponseEntity<Void> createSeason(@RequestBody SeasonAddRequestVo seasonAddRequestVo) {
        log.info("seasonAddRequestVo = {}", seasonAddRequestVo);
        SeasonAddRequestDto seasonAddRequestDto = SeasonAddRequestDto.builder()
                .name(seasonAddRequestVo.getName())
                .description(seasonAddRequestVo.getDescription())
                .startDate(seasonAddRequestVo.getStartDate())
                .endDate(seasonAddRequestVo.getEndDate())
                .discount(seasonAddRequestVo.getDiscount())
                .build();
        seasonService.addSeason(seasonAddRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "시즌 등록 성공",
                null
        );
    }

    @PutMapping
    public CommonResponseEntity<Void> updateSeason(@RequestBody SeasonUpdateRequestVo seasonUpdateRequestVo) {
        log.info("seasonUpdateRequestVo = {}", seasonUpdateRequestVo);
        SeasonUpdateRequestDto seasonUpdateRequestDto = SeasonUpdateRequestDto.builder()
                .id(seasonUpdateRequestVo.getId())
                .name(seasonUpdateRequestVo.getName())
                .description(seasonUpdateRequestVo.getDescription())
                .startDate(seasonUpdateRequestVo.getStartDate())
                .endDate(seasonUpdateRequestVo.getEndDate())
                .discount(seasonUpdateRequestVo.getDiscount())
                .build();
        seasonService.updateSeason(seasonUpdateRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "시즌 업데이트 성공",
                null
        );
    }

    @DeleteMapping
    public CommonResponseEntity<Void> deleteSeason(@RequestParam Long id) {
        log.info("seasonId = {}", id);
        seasonService.deleteSeason(id);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "시즌 삭제 성공",
                null
        );
    }

    @GetMapping
    public CommonResponseEntity<List<SeasonResponseVo>> getAllSeason() {
        List<SeasonResponseDto> seasonResponseDtos = seasonService.getAllSeason();
        List<SeasonResponseVo> seasonResponseVos = seasonResponseDtos.stream()
                .map(SeasonResponseDto::toResponseVo)
                .toList();
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "시즌 전체 조회 성공",
                seasonResponseVos
        );
    }

    @GetMapping("/{id}")
    public CommonResponseEntity<SeasonResponseVo> getOneSeason(@PathVariable Long id) {
        SeasonResponseDto seasonResponseDto = seasonService.getOneSeason(id);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "시즌 조회 성공",
                seasonResponseDto.toResponseVo()
        );
    }
}
