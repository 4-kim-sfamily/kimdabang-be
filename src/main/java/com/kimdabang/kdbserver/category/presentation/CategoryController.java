package com.kimdabang.kdbserver.category.presentation;


import com.kimdabang.kdbserver.category.application.CategoryService;
import com.kimdabang.kdbserver.category.dto.in.CategoryRequestDto;
import com.kimdabang.kdbserver.category.dto.out.CategoryResponseDto;
import com.kimdabang.kdbserver.category.vo.in.CategoryRequestVo;
import com.kimdabang.kdbserver.category.vo.out.CategoryResponseVo;
import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "CategoryListGet API", description = "CategoryListGet API 입니다.", tags = {"category-controller"})
    @GetMapping("/get-categorylist")
    public CommonResponseEntity<List<CategoryResponseVo>> getCategoryList(
    ) throws ParseException {
        List<CategoryResponseDto> categoryResponseDtoList = categoryService.getCategoryList();
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "카테고리 조회 성공",
                categoryResponseDtoList.stream()
                        .map(CategoryResponseDto::toResponseVo)
                        .toList()
        );
    }

    @Operation(summary = "CategoryGet API", description = "CategoryGet API 입니다.", tags = {"category-controller"})
    @GetMapping("/get-category")
    public CommonResponseEntity<CategoryResponseVo> getCategory(
            @RequestParam(value = "id") Long id
    ) throws ParseException {
        CategoryResponseDto categoryResponseDto = categoryService.getCategory(id);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "카테고리 조회 성공",
                categoryResponseDto.toResponseVo()
        );
    }

    @Operation(summary = "CategoryAdd API", description = "CategoryAdd API 입니다.", tags = {"category-controller"})
    @PostMapping("/add-category")
    public CommonResponseEntity<Void> addCategory(
            @RequestBody CategoryRequestVo categoryRequestVo
            ) throws ParseException {
        categoryService.addCategory(CategoryRequestDto.toRequestDto(categoryRequestVo));
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "카테고리 추가 성공",
                null
        );
    }
        @Operation(summary = "CategoryDelete API", description = "CategoryDelete API 입니다.", tags = {"category-controller"})
        @DeleteMapping("/delete-category")
        public CommonResponseEntity<Void> deleteCategory(
                @RequestParam(value = "id") Long id
            ) throws ParseException {
            categoryService.deleteCategory(id);
            return new CommonResponseEntity<>(
                    HttpStatus.OK,
                    "카테고리 삭제 성공",
                    null
            );
    }

}
