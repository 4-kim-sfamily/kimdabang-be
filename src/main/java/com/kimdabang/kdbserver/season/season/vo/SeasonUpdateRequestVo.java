package com.kimdabang.kdbserver.season.season.vo;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class SeasonUpdateRequestVo {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int discount;
}
