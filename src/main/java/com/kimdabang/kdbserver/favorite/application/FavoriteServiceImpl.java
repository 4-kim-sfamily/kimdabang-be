package com.kimdabang.kdbserver.favorite.application;

import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.common.jwt.JwtTokenProvider;
import com.kimdabang.kdbserver.favorite.domain.Favorite;
import com.kimdabang.kdbserver.favorite.dto.in.FavoriteRequestDto;
import com.kimdabang.kdbserver.favorite.dto.out.FavoriteCheckResponseDto;
import com.kimdabang.kdbserver.favorite.dto.out.FavoriteResponseDto;
import com.kimdabang.kdbserver.favorite.infrastructure.FavoriteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.FAVORITE_NOT_FOUND;
import static com.kimdabang.kdbserver.common.exception.ErrorCode.USER_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public FavoriteCheckResponseDto getFavoriteCheck(String productCode, String authorization) {

        String userUuid = jwtTokenProvider.useToken(authorization);

        Favorite favorite = favoriteRepository.findByProductCodeAndUserUuid(productCode, userUuid)
                .orElse(null);

        boolean favoriteStatus = (favorite != null && !favorite.isCanceled());

        return new FavoriteCheckResponseDto(favoriteStatus);
    }

    @Override
    public void addFavorite(FavoriteRequestDto favoriteDto) {

        String userUuid = jwtTokenProvider.useToken(favoriteDto.getAccessToken());

        favoriteRepository.save(favoriteDto.toFavoriteEntity(userUuid, false));

    }

    @Override
    public FavoriteCheckResponseDto putFavorite(FavoriteRequestDto favoriteDto) {

        String userUuid = jwtTokenProvider.useToken(favoriteDto.getAccessToken());

        Favorite favorite = favoriteRepository.findByProductCodeAndUserUuid(favoriteDto.getProductCode(), userUuid)
                .orElse(null);

        boolean favoriteStatus;

        if (favorite == null) {
            // 좋아요 데이터가 없으면 새로운 데이터 추가
            addFavorite(favoriteDto);
            favoriteStatus = true; // 새로운 데이터가 추가되었으므로 기본값 false
        } else {
            // 좋아요 데이터가 있으면 is_canceled 값을 변경
            favorite.change();
            favoriteRepository.save(favorite);
            favoriteStatus = !favorite.isCanceled(); // 변경된 is_canceled 값을 가져옴
        }

        return new FavoriteCheckResponseDto(favoriteStatus);
    }

    @Override
    public List<FavoriteResponseDto> getAllFavorites(String authorization) {

        String userUuid = jwtTokenProvider.useToken(authorization);
        List<Favorite> favoriteList = favoriteRepository.findAllByUserUuid(userUuid);
        if (favoriteList != null) {
            return favoriteList.stream()
                    .filter(favorite -> !favorite.isCanceled())
                    .map(favorite -> FavoriteResponseDto.builder()
                            .productCode(favorite.getProductCode())
                            .build())
                    .toList();
        }
        return List.of();
    }
}
