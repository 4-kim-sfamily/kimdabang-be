package com.kimdabang.kdbserver.restock.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestockResponseVo {
    private Date restockDate;
    private Boolean status;
    private String productCode;
    private Date calledDate;
}
