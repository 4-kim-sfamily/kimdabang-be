package com.kimdabang.kdbserver.product.score.application;

import com.kimdabang.kdbserver.common.dto.PageResponseDto;
import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.product.score.domain.ProductScore;
import com.kimdabang.kdbserver.product.score.dto.ProductBestResponseDto;
import com.kimdabang.kdbserver.product.score.dto.ScoreResponseDto;
import com.kimdabang.kdbserver.product.score.infrastructure.ProductScoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.DATA_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductScoreServiceImpl implements ProductScoreService{

    private final ProductScoreRepository productScoreRepository;

    @Override
    public PageResponseDto getCategoryBestList(Long categoryId, Integer page, Integer size) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "score"));
        Page<ProductScore> productScoreList = productScoreRepository.findByCategoryId(categoryId, pageRequest);

        List<ProductBestResponseDto> productBestResponseDtoList = productScoreList.stream()
                .map(ProductBestResponseDto::toResponseDto)
                .toList();

        return PageResponseDto.toResponseDto(
                page, productScoreList.getTotalPages(), productScoreList.hasNext(), productBestResponseDtoList);
    }
    @Override
    public PageResponseDto getBestList(Integer page, Integer size) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "score"));
        Page<ProductScore> productScoreList = productScoreRepository.findAll(pageRequest);

        List<ProductBestResponseDto> productBestResponseDtoList = productScoreList.stream()
                .map(ProductBestResponseDto::toResponseDto)
                .toList();

        return PageResponseDto.toResponseDto(
                page, productScoreList.getTotalPages(), productScoreList.hasNext(), productBestResponseDtoList);
    }
    @Override
    public ScoreResponseDto getScore(String productCode) {
        ProductScore productScore = productScoreRepository.findByProductCode(productCode)
                .orElseThrow(() -> new CustomException(DATA_NOT_FOUND));
        return ScoreResponseDto.toResponseDto(productScore);
    }

}
