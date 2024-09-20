package com.kimdabang.kdbserver.category.vo.out;

import com.kimdabang.kdbserver.category.dto.out.CategoryResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseVo {

    private Long id;
    private String name;
    private Long depth;
    private List<CategoryResponseDto> children;
}
