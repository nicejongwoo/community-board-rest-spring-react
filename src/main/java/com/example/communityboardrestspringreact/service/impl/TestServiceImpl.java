package com.example.communityboardrestspringreact.service.impl;

import com.example.communityboardrestspringreact.domain.Test;
import com.example.communityboardrestspringreact.repository.TestRepository;
import com.example.communityboardrestspringreact.service.TestService;
import com.example.communityboardrestspringreact.web.dto.mapper.TestDtoMapper;
import com.example.communityboardrestspringreact.web.dto.request.TestRequest;
import com.example.communityboardrestspringreact.web.dto.response.TestListResponse;
import com.example.communityboardrestspringreact.web.dto.response.TestResponse;
import com.example.communityboardrestspringreact.web.dto.search.TestSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    @Transactional
    @Override
    public Long save(TestRequest request) {
        Test test = TestDtoMapper.MAPPER.toEntity(request);
        testRepository.save(test);
        return test.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<TestListResponse> search(TestSearch search, Pageable pageable) {
        return testRepository.search(search, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public TestResponse getTest(Long id) {
        Test test = checkTestEntity(id);
        return TestDtoMapper.MAPPER.toDto(test);
    }

    @Transactional
    @Override
    public TestResponse edit(Long id, TestRequest request) {
        Test test = checkTestEntity(id);
        test.edit(request);
        return TestDtoMapper.MAPPER.toDto(test);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Test test = checkTestEntity(id);
        testRepository.delete(test);
    }

    private Test checkTestEntity(Long id) {
        return testRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not Found Test")
        );
    }
}
