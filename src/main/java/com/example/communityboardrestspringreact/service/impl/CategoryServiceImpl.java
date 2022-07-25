package com.example.communityboardrestspringreact.service.impl;

import com.example.communityboardrestspringreact.domain.Category;
import com.example.communityboardrestspringreact.repository.CategoryRepository;
import com.example.communityboardrestspringreact.service.CategoryService;
import com.example.communityboardrestspringreact.web.dto.mapper.CategoryDtoMapper;
import com.example.communityboardrestspringreact.web.dto.request.CategoryRequest;
import com.example.communityboardrestspringreact.web.dto.response.CategoryResponse;
import com.example.communityboardrestspringreact.web.dto.search.CategorySearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public Long register(CategoryRequest request) {
        Category category = CategoryDtoMapper.MAPPER.toEntity(request);
        categoryRepository.save(category);
        return category.getId();
    }

    @Override
    public Page<CategoryResponse> search(CategorySearch search, Pageable pageable) {
        return null;
    }

    @Override
    public CategoryResponse getOne(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
        //todo category -> response
        return null;
    }

    @Override
    public void edit(Long id, CategoryRequest request) {
        //todo check category exist return category
        //todo category update
    }

    @Override
    public void delete(Long id) {
        //todo check category exist
        //todo delete
    }
}
