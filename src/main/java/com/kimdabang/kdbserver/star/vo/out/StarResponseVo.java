package com.kimdabang.kdbserver.star.vo.out;

import lombok.*;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StarResponseVo {
    private Date expirationDate;
    private Date createdAt;
    private Boolean isEcho;
    private Integer starAmount;
    private String description;
}
