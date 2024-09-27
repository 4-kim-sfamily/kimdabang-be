package com.kimdabang.kdbserver.restock.application;

import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.common.jwt.JwtTokenProvider;
import com.kimdabang.kdbserver.restock.domain.Restock;
import com.kimdabang.kdbserver.restock.dto.in.RestockRequestDto;
import com.kimdabang.kdbserver.restock.dto.out.RestockResponseDto;
import com.kimdabang.kdbserver.restock.infrastructure.RestockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.DATA_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
public class RestockServiceImpl implements RestockService {
    private final JwtTokenProvider jwtTokenProvider;
    private final RestockRepository restockRepository;

    @Override
    public List<RestockResponseDto> getRestock(String authorization) {
        List<Restock> restocks = restockRepository.findByUuid(jwtTokenProvider.useToken(authorization));
        log.info("restocks: {}", restocks);
        if (restocks != null) {
            return restocks.stream()
                    .map(RestockResponseDto::toResponseDto)
                    .toList();
        }
        return List.of();
    }
    @Override
    public void addRestock(RestockRequestDto restockRequestDto, String authorization) {
        String uuid = jwtTokenProvider.useToken(authorization);

        Optional<Restock> existingRestock = restockRepository.findByUuidAndProductCode(uuid, restockRequestDto.getProductCode());

        if (existingRestock.isEmpty()) {
            restockRepository.save(restockRequestDto.toEntity(uuid));
        }
    }
    @Override
    public void deleteRestock(String productCode, String authorization) {
        Restock deleteRestock = restockRepository.findByUuidAndProductCode(jwtTokenProvider.useToken(authorization), productCode)
                .orElseThrow(() -> new CustomException(DATA_NOT_FOUND));
        restockRepository.delete(deleteRestock);
    }

}
