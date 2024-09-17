package com.kimdabang.kdbserver.favorite.application;

import com.kimdabang.kdbserver.favorite.dto.in.FavoriteRequestDto;
import com.kimdabang.kdbserver.favorite.dto.out.FavoriteCheckResponseDto;
import com.kimdabang.kdbserver.favorite.dto.out.FavoriteResponseDto;

import java.util.List;

public interface FavoriteService {

    void addFavorite(FavoriteRequestDto favoriteRequestDto);
    FavoriteCheckResponseDto putFavorite(FavoriteRequestDto favoriteRequestDto);
    FavoriteCheckResponseDto getFavoriteCheck(String productCode, String Authorization);
    List<FavoriteResponseDto> getAllFavorites(String Authorization);

}
