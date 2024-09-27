package com.kimdabang.kdbserver.restock.dto.in;

import com.kimdabang.kdbserver.restock.domain.Restock;
import com.kimdabang.kdbserver.restock.vo.in.RestockRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestockRequestDto {

    private String productCode;

    public static RestockRequestDto toRequestDto(RestockRequestVo restockRequestVo) {
        return RestockRequestDto.builder()
                .productCode(restockRequestVo.getProductCode())
                .build();
    }

    public Restock toEntity(String uuid) {
        return Restock.builder()
                .restockDate(new Date())
                .productCode(productCode)
                .status(false)
                .uuid(uuid)
                .build();
    }
}
