package com.kimdabang.kdbserver.category.application;

import com.kimdabang.kdbserver.category.dto.in.CategoryRequestDto;
import com.kimdabang.kdbserver.category.dto.out.CategoryResponseDto;

import java.util.List;


public interface CategoryService {
    List<CategoryResponseDto> getCategoryList();
    CategoryResponseDto getCategory(Long id);
    List<CategoryResponseDto> getLeafCategoryList();
    void addCategory(CategoryRequestDto categoryRequestDto);
    void deleteCategory(Long id);
}
