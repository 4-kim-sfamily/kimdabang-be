package com.kimdabang.kdbserver.star.vo.in;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
public class StarRequestVo {
    private Date startDate;
    private Date endDate;
    private String accessToken;
}
