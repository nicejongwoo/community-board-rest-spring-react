package com.example.communityboardrestspringreact.service;

import com.example.communityboardrestspringreact.web.dto.request.CategoryRequest;
import com.example.communityboardrestspringreact.web.dto.response.CategoryResponse;
import com.example.communityboardrestspringreact.web.dto.search.CategorySearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Long register(CategoryRequest request);

    Page<CategoryResponse> search(CategorySearch search, Pageable pageable);

    CategoryResponse getOne(Long id);

    void edit(Long id, CategoryRequest request);

    void delete(Long id);

}
