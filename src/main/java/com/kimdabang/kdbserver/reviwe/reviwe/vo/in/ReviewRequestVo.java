package com.kimdabang.kdbserver.reviwe.reviwe.vo.in;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReviewRequestVo {

    private String productCode;
    private String options;
    private Integer rating;
    private String text;
    private String mediaType;
    private String mediaURL;
}
