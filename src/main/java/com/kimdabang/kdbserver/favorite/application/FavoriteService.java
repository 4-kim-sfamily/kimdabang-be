package com.kimdabang.kdbserver.favorite.application;

import com.kimdabang.kdbserver.favorite.dto.in.FavoriteRequestDto;
import com.kimdabang.kdbserver.favorite.dto.out.FavoriteCheckResponseDto;
import com.kimdabang.kdbserver.favorite.dto.out.FavoriteResponseDto;

import java.util.List;

public interface FavoriteService {

    void addFavorite(FavoriteRequestDto favoriteRequestDto);
    void putFavorite(FavoriteRequestDto favoriteRequestDto);
    FavoriteCheckResponseDto getFavoriteCheck(String productCode, String Authorization);
    List<FavoriteResponseDto> getAllFavorites(String Authorization);

    // todo: Category 구현 후 유효한 좋아요 리스트 api 개발
    //    List<FavoriteResponseDto> getAllValidFavorite;

}
