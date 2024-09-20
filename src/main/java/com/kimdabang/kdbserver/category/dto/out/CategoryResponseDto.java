package com.kimdabang.kdbserver.category.dto.out;

import com.kimdabang.kdbserver.category.domain.Category;
import com.kimdabang.kdbserver.category.vo.out.CategoryResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponseDto {
    private Long id;
    private String name;
    private Long depth;
    private List<CategoryResponseDto> children;

    public static CategoryResponseDto of(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getDepth(),
                category.getChildren().stream().map(CategoryResponseDto::of).collect(Collectors.toList())
        );
    }
    public CategoryResponseVo toResponseVo() {
        return CategoryResponseVo.builder()
                .id(id)
                .depth(depth)
                .name(name)
                .children(children)
                .build();
    }
}
