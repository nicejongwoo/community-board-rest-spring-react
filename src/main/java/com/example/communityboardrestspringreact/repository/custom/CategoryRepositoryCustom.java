package com.example.communityboardrestspringreact.repository.custom;

import com.example.communityboardrestspringreact.web.dto.response.CategoryResponse;
import com.example.communityboardrestspringreact.web.dto.search.CategorySearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryRepositoryCustom {
    Page<CategoryResponse> search(CategorySearch search, Pageable pageable);
}
