package com.kimdabang.kdbserver.restock.application;

import com.kimdabang.kdbserver.restock.dto.in.RestockRequestDto;
import com.kimdabang.kdbserver.restock.dto.out.RestockResponseDto;

import java.util.List;

public interface RestockService {
    List<RestockResponseDto> getRestock(String Authorization);
    void addRestock(RestockRequestDto restockRequestDto, String Authorization);
    void deleteRestock(String productCode, String Authorization);
}
