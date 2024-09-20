package com.kimdabang.kdbserver.category.application;

import com.kimdabang.kdbserver.category.domain.Category;
import com.kimdabang.kdbserver.category.dto.in.CategoryRequestDto;
import com.kimdabang.kdbserver.category.dto.out.CategoryResponseDto;
import com.kimdabang.kdbserver.category.infrastructure.CategoryRepository;
import com.kimdabang.kdbserver.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.DATA_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDto> getCategoryList(){
        List<Category> categoryList= categoryRepository.findByParentIsNullAndActiveTrue();
        log.info("categories: {}", categoryList);
        if (categoryList != null) {
            return categoryList.stream()
                    .map(CategoryResponseDto::of) // 메서드 참조를 사용하여 간결화
                    .toList();
        }
        return List.of();
    }

    @Override
    public CategoryResponseDto getCategory(Long id){
        Category category= categoryRepository.findByIdAndActiveTrue(id).orElseThrow(() -> new CustomException(DATA_NOT_FOUND));
        log.info("category: {}", category);
        return CategoryResponseDto.of(category);
    }
    @Override
    public void addCategory(CategoryRequestDto categoryRequestDto) {
        if(categoryRequestDto.getParentId() != null && categoryRequestDto.getParentId() != 0) {
            Category parentCategory = categoryRepository.findByIdAndActiveTrue(categoryRequestDto.getParentId())
                    .orElseThrow(() -> new CustomException(DATA_NOT_FOUND));
            if (parentCategory.getDepth() == null) {
                categoryRepository.save(categoryRequestDto.toEntity(parentCategory, 1L));
            }
            else {
                categoryRepository.save(categoryRequestDto.toEntity(parentCategory, parentCategory.getDepth()+1));
            }
        }
        else {
            categoryRepository.save(categoryRequestDto.toEntity(null, null));
        }
    }
    @Override
    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new CustomException(DATA_NOT_FOUND));
        category.updateActive(false);
        categoryRepository.save(category);
    }
}
