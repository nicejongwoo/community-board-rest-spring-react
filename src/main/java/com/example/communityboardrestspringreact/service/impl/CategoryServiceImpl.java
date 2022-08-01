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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

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
        return categoryRepository.search(search, pageable);
    }

    @Override
    public CategoryResponse getOne(Long id) {
        Category category = checkCategory(id);
        return CategoryDtoMapper.MAPPER.toDto(category);
    }

    @Transactional
    @Override
    public void edit(Long id, CategoryRequest request) {
        Category category = checkCategory(id);
        authorityCheck(category.getCreatedBy());

        category.update(request.getName(), request.getType());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Category category = checkCategory(id);
        authorityCheck(category.getCreatedBy());

        categoryRepository.delete(category);
    }

    private void authorityCheck(String createdBy) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        boolean owner = createdBy.equals(authentication.getName());
        boolean admin = authorities.stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().contains("ADMIN"));

        if(!(owner || admin)) {
            throw new AccessDeniedException("해당 요청에 대한 권한이 없습니다.");
        }
    }

    public Category checkCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

}
