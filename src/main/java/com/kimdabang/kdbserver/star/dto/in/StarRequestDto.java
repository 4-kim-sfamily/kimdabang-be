package com.kimdabang.kdbserver.star.dto.in;

import lombok.*;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StarRequestDto {
    private Date startDate;
    private Date endDate;
    private String accessToken;
}
