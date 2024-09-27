package com.kimdabang.kdbserver.favorite.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.favorite.application.FavoriteService;
import com.kimdabang.kdbserver.favorite.dto.in.FavoriteRequestDto;
import com.kimdabang.kdbserver.favorite.dto.out.FavoriteCheckResponseDto;
import com.kimdabang.kdbserver.favorite.dto.out.FavoriteResponseDto;
import com.kimdabang.kdbserver.favorite.vo.FavoriteCheckResponseVo;
import com.kimdabang.kdbserver.favorite.vo.FavoriteRequestVo;
import com.kimdabang.kdbserver.favorite.vo.FavoriteResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping("/{productCode}")
    public CommonResponseEntity<FavoriteCheckResponseVo> getFavoriteCheck(
            @RequestHeader ("Authorization") String authorization,
            @PathVariable String productCode) {
        FavoriteCheckResponseDto favoriteCheckResponseDto = favoriteService.getFavoriteCheck(productCode, authorization);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "개별 좋아요 상태 조회 성공",
                favoriteCheckResponseDto.toVo()
        );
    }

    @PutMapping("/{productCode}")
    public CommonResponseEntity<FavoriteCheckResponseVo> changeFavoriteCheck(
            @RequestHeader ("Authorization") String authorization,
            @PathVariable String productCode) {
        FavoriteRequestDto favoriteRequestDto = FavoriteRequestDto.builder()
                .productCode(productCode)
                .accessToken(authorization)
                .build();
        FavoriteCheckResponseDto favoriteCheckResponseDto = favoriteService.putFavorite(favoriteRequestDto);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "좋아요 상태 업데이트 성공",
                favoriteCheckResponseDto.toVo()
        );
    }

    @GetMapping("/list")
    public CommonResponseEntity<List<FavoriteResponseVo>> getFavoriteCheckList(
            @RequestHeader ("Authorization") String authorization) {
        List<FavoriteResponseDto> favoriteResponseDtoList = favoriteService.getAllFavorites(authorization);

        List<FavoriteResponseVo> favoriteResponseVoList
                = favoriteResponseDtoList.stream()
                .map(FavoriteResponseDto::toFavoriteResponseVo)
                .toList();

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "좋아요 리스트 조회 성공",
                favoriteResponseVoList
        );
    }
}
