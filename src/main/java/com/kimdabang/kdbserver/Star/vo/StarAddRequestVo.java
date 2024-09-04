package com.kimdabang.kdbserver.Star.vo;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
public class StarAddRequestVo {
    private String accesstoken;
    private Date expirationDate;
    private String description;
    private Boolean isEcho;
    private Integer starAmount;
}
