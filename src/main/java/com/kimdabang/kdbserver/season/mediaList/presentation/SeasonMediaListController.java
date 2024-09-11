package com.kimdabang.kdbserver.season.mediaList.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.season.mediaList.application.SeasonMediaListService;
import com.kimdabang.kdbserver.season.mediaList.dto.in.SeasonMediaListRequestDto;
import com.kimdabang.kdbserver.season.mediaList.vo.SeasonMediaListRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/season-media-list")
public class SeasonMediaListController {

    private final SeasonMediaListService seasonMediaListService;

    @PostMapping
    public CommonResponseEntity<Void> createSeasonMediaList(@RequestBody SeasonMediaListRequestVo seasonMediaListRequestVo) {
        log.info("seasonMediaListRequestVo = {}", seasonMediaListRequestVo);
        SeasonMediaListRequestDto seasonMediaListRequestDto = SeasonMediaListRequestDto.builder()
                .seasonId(seasonMediaListRequestVo.getSeasonId())
                .mediaURL(seasonMediaListRequestVo.getMediaURL())
                .imageName(seasonMediaListRequestVo.getImageName())
                .mediaType(seasonMediaListRequestVo.getMediaType())
                .build();
        seasonMediaListService.addSeasonMediaList(seasonMediaListRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "시즌 미디어 리스트 등록 성공",
                null
        );
    }
}
