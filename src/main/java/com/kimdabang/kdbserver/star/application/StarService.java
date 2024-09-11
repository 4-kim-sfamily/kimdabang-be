package com.kimdabang.kdbserver.star.application;

import com.kimdabang.kdbserver.star.dto.in.StarAddRequestDto;
import com.kimdabang.kdbserver.star.dto.out.StarAmountResponseDto;
import com.kimdabang.kdbserver.star.dto.out.StarResponseDto;

import java.util.Date;
import java.util.List;

public interface StarService {
    List<StarResponseDto> getStar(Date start, Date end, String Authorization);
    void addStar(StarAddRequestDto starAddRequestDto, String Authorization);
    StarAmountResponseDto getStarAmount(String Authorization);
}
