package com.example.communityboardrestspringreact.web.api;

import com.example.communityboardrestspringreact.domain.Test;
import com.example.communityboardrestspringreact.repository.TestRepository;
import com.example.communityboardrestspringreact.service.TestService;
import com.example.communityboardrestspringreact.web.dto.request.TestRequest;
import com.example.communityboardrestspringreact.web.dto.search.TestSearch;
import com.example.communityboardrestspringreact.web.dto.response.CommonApiResponse;
import com.example.communityboardrestspringreact.web.dto.response.TestListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@RequiredArgsConstructor
@RequestMapping("/api/test")
@RestController
public class TestController {

    private final Environment environment;

    private final TestRepository testRepository;
    private final TestService testService;

    @PostConstruct
    public void run() {
        if (Arrays.asList(environment.getActiveProfiles()).contains("dev")) {
            initData();
        }
    }

    private void initData() {
        for (int i = 0; i < 114; i++) {
            Test test = Test.builder()
                    .title("TEST TITLE " + i)
                    .content("TEST CONTENT " + i)
                    .createdBy("admin")
                    .deleted(false)
                    .notice(false)
                    .build();
            testRepository.save(test);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody TestRequest request) {
        Long id = testService.save(request);
        return new ResponseEntity<>(CommonApiResponse.success(id), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> getList(TestSearch search, Pageable pageable) {
        Page<TestListResponse> page = testService.search(search, pageable);
        return ResponseEntity.ok(CommonApiResponse.success(page));
    }

}
