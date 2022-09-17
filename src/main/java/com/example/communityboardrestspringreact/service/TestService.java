package com.example.communityboardrestspringreact.service;

import com.example.communityboardrestspringreact.web.dto.request.TestRequest;
import com.example.communityboardrestspringreact.web.dto.search.TestSearch;
import com.example.communityboardrestspringreact.web.dto.response.TestListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TestService {
    Long save(TestRequest request);

    Page<TestListResponse> search(TestSearch search, Pageable pageable);
}
