package com.example.communityboardrestspringreact.web.api;

import com.example.communityboardrestspringreact.domain.Test;
import com.example.communityboardrestspringreact.repository.TestRepository;
import com.example.communityboardrestspringreact.web.dto.request.TestRequest;
import com.example.communityboardrestspringreact.web.dto.request.TestSearch;
import com.example.communityboardrestspringreact.web.dto.response.CommonApiResponse;
import com.example.communityboardrestspringreact.web.dto.response.TestListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@RequiredArgsConstructor
@RequestMapping("/api/test")
@RestController
public class TestController {

    private final TestRepository testRepository;

    private final Environment environment;

    @PostConstruct
    public void run() {
        if (Arrays.asList(environment.getActiveProfiles()).contains("dev")) {
            initData();
        }
    }

    private void initData() {
        for (int i = 0; i < 114; i++) {
            Test test = Test.builder()
                    .content("Content" + i)
                    .useYn(true)
                    .useEnabled(false)
                    .build();
            testRepository.save(test);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody TestRequest test) {
        Test build = Test.builder().useEnabled(test.getUseEnabled()).useYn(test.getUseYn()).build();
        Test save = testRepository.save(build);
        return ResponseEntity.ok(save);
    }

    @GetMapping("")
    public ResponseEntity<?> getList(TestSearch search, Pageable pageable) {
        Page<TestListResponse> page = testRepository.search(search, pageable);
        return ResponseEntity.ok(CommonApiResponse.success(page));
    }

}
