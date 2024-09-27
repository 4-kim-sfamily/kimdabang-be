package com.kimdabang.kdbserver.review.review.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
@Builder
public class ReviewResponseVo {
    private Long reviewCode;
    private String productCode;
    private String nickName;
    private String options;
    private Date creationDate;
    private Integer rating;
    private String text;
}
