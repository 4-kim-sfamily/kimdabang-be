package com.kimdabang.kdbserver.season.productList.application;

import com.kimdabang.kdbserver.season.productList.dto.in.SeasonProductListAddRequestDto;
import com.kimdabang.kdbserver.season.productList.dto.in.SeasonProductListUpdateRequestDto;
import com.kimdabang.kdbserver.season.productList.dto.out.SeasonProductListResponseDto;

import java.util.List;

public interface SeasonProductListService {

    void addSeasonProductList(SeasonProductListAddRequestDto seasonProductListAddRequestDto);
    void updateSeasonProductList(SeasonProductListUpdateRequestDto seasonProductListUpdateRequestDto);
    void deleteSeasonProductList(Long id);
    SeasonProductListResponseDto getOneSeasonProductList(Long id);
    List<SeasonProductListResponseDto> getAllSeasonProductList();
}
