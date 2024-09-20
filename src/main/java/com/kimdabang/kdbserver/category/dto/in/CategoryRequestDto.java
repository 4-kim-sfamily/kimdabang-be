package com.kimdabang.kdbserver.category.dto.in;

import com.kimdabang.kdbserver.category.domain.Category;
import com.kimdabang.kdbserver.category.dto.out.CategoryResponseDto;
import com.kimdabang.kdbserver.category.vo.in.CategoryRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequestDto {
    private String name;
    private Long parentId;

    public static CategoryRequestDto toRequestDto(CategoryRequestVo categoryRequestVo) {
        return CategoryRequestDto.builder()
                .name(categoryRequestVo.getName())
                .parentId(categoryRequestVo.getParentId())
                .build();
    }
    public Category toEntity(Category parent, Long depth) {
        return Category.builder()
                .name(name)
                .parent(parent)
                .depth(depth)
                .build();
    }
}
