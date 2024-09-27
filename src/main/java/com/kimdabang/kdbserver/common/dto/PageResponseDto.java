package com.kimdabang.kdbserver.common.dto;

import com.kimdabang.kdbserver.common.vo.PageResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDto {

    private Integer nowPage;
    private Integer totalPage;
    private Boolean nextPage;
    private List<?> data;

    public static PageResponseDto toResponseDto(Integer page, Integer totalPage,Boolean nextPage ,  List<?> data) {
        return PageResponseDto.builder()
                .nowPage(page)
                .totalPage(totalPage)
                .nextPage(nextPage)
                .data(data)
                .build();
    }
    public PageResponseVo toResponseVo() {
        return PageResponseVo.builder()
                .nowPage(nowPage)
                .totalPage(totalPage)
                .nextPage(nextPage)
                .data(data)
                .build();
    }
}
