package com.kimdabang.kdbserver.product.score.application;

import com.kimdabang.kdbserver.common.dto.PageResponseDto;
import com.kimdabang.kdbserver.product.score.dto.ScoreResponseDto;


public interface ProductScoreService {

    PageResponseDto getCategoryBestList(Long categoryId,Integer page, Integer size);
    PageResponseDto getBestList(Integer page, Integer size);
    ScoreResponseDto getScore(String productCode);
}
