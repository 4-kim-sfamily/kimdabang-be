package com.kimdabang.kdbserver.season.mediaList.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.season.mediaList.application.SeasonMediaListService;
import com.kimdabang.kdbserver.season.mediaList.domain.SeasonMediaList;
import com.kimdabang.kdbserver.season.mediaList.dto.in.SeasonMediaListAddRequestDto;
import com.kimdabang.kdbserver.season.mediaList.dto.in.SeasonMediaListUpdateRequestDto;
import com.kimdabang.kdbserver.season.mediaList.vo.SeasonMediaListAddRequestVo;
import com.kimdabang.kdbserver.season.mediaList.vo.SeasonMediaListUpdateRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/season-media-list")
public class SeasonMediaListController {

    private final SeasonMediaListService seasonMediaListService;

    @PostMapping
    public CommonResponseEntity<Void> createSeasonMediaList(@RequestBody SeasonMediaListAddRequestVo seasonMediaListAddRequestVo) {
        log.info("seasonMediaListAddRequestVo = {}", seasonMediaListAddRequestVo);
        SeasonMediaListAddRequestDto seasonMediaListAddRequestDto = SeasonMediaListAddRequestDto.builder()
                .seasonId(seasonMediaListAddRequestVo.getSeasonId())
                .mediaURL(seasonMediaListAddRequestVo.getMediaURL())
                .imageName(seasonMediaListAddRequestVo.getImageName())
                .mediaType(seasonMediaListAddRequestVo.getMediaType())
                .build();
        seasonMediaListService.addSeasonMediaList(seasonMediaListAddRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "시즌 미디어 리스트 등록 성공",
                null
        );
    }

    @PutMapping
    public CommonResponseEntity<Void> updateSeasonMediaList(@RequestBody SeasonMediaListUpdateRequestVo seasonMediaListUpdateRequestVo) {
        log.info("seasonMediaListUpdateRequestVo = {}", seasonMediaListUpdateRequestVo);
        SeasonMediaListUpdateRequestDto seasonMediaListUpdateRequestDto = SeasonMediaListUpdateRequestDto.builder()
                .id(seasonMediaListUpdateRequestVo.getId())
                .seasonId(seasonMediaListUpdateRequestVo.getSeasonId())
                .mediaURL(seasonMediaListUpdateRequestVo.getMediaURL())
                .imageName(seasonMediaListUpdateRequestVo.getImageName())
                .mediaType(seasonMediaListUpdateRequestVo.getMediaType())
                .build();
        seasonMediaListService.updateSeasonMediaList(seasonMediaListUpdateRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "시즌 미디어 리스트 업데이트 성공",
                null
        );
    }

    @DeleteMapping
    public CommonResponseEntity<Void> deleteSeasonMediaList(@RequestParam Long id) {
        log.info("seasonMediaListId = {}", id);
        seasonMediaListService.deleteSeasonMediaList(id);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "시즌 미디어 리스트 삭제 성공",
                null
        );
    }
}
