package com.kimdabang.kdbserver.common.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class PageResponseVo {

    private Integer nowPage;
    private Integer totalPage;
    private Boolean nextPage;
    private List<?> data;
}
