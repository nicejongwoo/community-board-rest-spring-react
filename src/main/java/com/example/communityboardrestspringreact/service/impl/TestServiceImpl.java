package com.example.communityboardrestspringreact.service.impl;

import com.example.communityboardrestspringreact.domain.Test;
import com.example.communityboardrestspringreact.repository.TestRepository;
import com.example.communityboardrestspringreact.service.TestService;
import com.example.communityboardrestspringreact.web.dto.mapper.TestDtoMapper;
import com.example.communityboardrestspringreact.web.dto.request.TestRequest;
import com.example.communityboardrestspringreact.web.dto.search.TestSearch;
import com.example.communityboardrestspringreact.web.dto.response.TestListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    @Override
    public Long save(TestRequest request) {
        Test test = TestDtoMapper.MAPPER.toEntity(request);
        testRepository.save(test);
        return test.getId();
    }

    @Override
    public Page<TestListResponse> search(TestSearch search, Pageable pageable) {
        return testRepository.search(search, pageable);
    }
}
