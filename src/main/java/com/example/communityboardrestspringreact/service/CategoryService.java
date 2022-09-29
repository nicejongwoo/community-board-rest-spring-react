package com.example.communityboardrestspringreact.service;

import com.example.communityboardrestspringreact.domain.Category;
import com.example.communityboardrestspringreact.web.dto.request.CategoryRequest;
import com.example.communityboardrestspringreact.web.dto.response.CategoryResponse;
import com.example.communityboardrestspringreact.web.dto.search.CategorySearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    Long register(CategoryRequest request);

    Page<CategoryResponse> search(CategorySearch search, Pageable pageable);

    CategoryResponse getOne(Long id);

    void edit(CategoryRequest request, Category category);

    void delete(Category category);

    Category checkCategory(Long id);

    List<CategoryResponse> getOptions();

}
