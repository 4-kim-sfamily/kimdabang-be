package com.kimdabang.kdbserver.review.review.vo.in;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReviewUpdateRequestVo {
    private Long reviewCode;
    private Integer rating;
    private String text;
    private String mediaType;
    private String mediaURL;
}
