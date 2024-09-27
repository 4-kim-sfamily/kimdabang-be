package com.kimdabang.kdbserver.category.vo.in;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class CategoryRequestVo {
    private String name;
    private Long parentId;
}
