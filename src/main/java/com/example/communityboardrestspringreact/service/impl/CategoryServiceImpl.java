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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    @Override
    public Long register(CategoryRequest request) {
        Category category = CategoryDtoMapper.MAPPER.toEntity(request);
        categoryRepository.save(category);
        return category.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CategoryResponse> search(CategorySearch search, Pageable pageable) {
        return categoryRepository.search(search, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryResponse getOne(Long id) {
        Category category = checkCategory(id);
        return CategoryDtoMapper.MAPPER.toDto(category);
    }

    @PreAuthorize("#category.createdBy == authentication.name OR hasRole('ROLE_ADMIN')")
    @Transactional
    @Override
    public void edit(CategoryRequest request, Category category) {
        category.update(request.getName(), request.getType());
    }

    @PreAuthorize("#category.createdBy == authentication.name OR hasRole('ROLE_ADMIN')")
    @Transactional
    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public Category checkCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not Found Category"));
    }

    @Override
    public List<CategoryResponse> getOptions() {
        List<Category> categories = categoryRepository.findAll();
        return CategoryDtoMapper.MAPPER.toListDto(categories);
    }

}
