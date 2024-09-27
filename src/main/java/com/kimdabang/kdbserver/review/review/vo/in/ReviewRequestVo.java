package com.kimdabang.kdbserver.review.review.vo.in;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReviewRequestVo {

    private String productCode;
    private Long purchaseCode;
    private String options;
    private Integer rating;
    private String text;
    private String mediaType;
    private String mediaURL;
}
