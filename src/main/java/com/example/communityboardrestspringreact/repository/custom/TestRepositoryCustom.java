package com.example.communityboardrestspringreact.repository.custom;

import com.example.communityboardrestspringreact.web.dto.search.TestSearch;
import com.example.communityboardrestspringreact.web.dto.response.TestListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TestRepositoryCustom {

    Page<TestListResponse> search(TestSearch search, Pageable pageable);

}
