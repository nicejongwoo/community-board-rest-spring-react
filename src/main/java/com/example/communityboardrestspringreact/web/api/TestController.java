package com.example.communityboardrestspringreact.web.api;

import com.example.communityboardrestspringreact.domain.Test;
import com.example.communityboardrestspringreact.repository.TestRepository;
import com.example.communityboardrestspringreact.security.service.CustomUserDetails;
import com.example.communityboardrestspringreact.service.TestService;
import com.example.communityboardrestspringreact.web.dto.request.TestRequest;
import com.example.communityboardrestspringreact.web.dto.response.CommonApiResponse;
import com.example.communityboardrestspringreact.web.dto.response.TestListResponse;
import com.example.communityboardrestspringreact.web.dto.response.TestResponse;
import com.example.communityboardrestspringreact.web.dto.search.TestSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.Arrays;

@RequiredArgsConstructor
@RequestMapping("/api/test")
@RestController
public class TestController {

    private final Environment environment;

    private final TestRepository testRepository;
    private final TestService testService;

//    @PostConstruct
//    public void run() {
//        if (Arrays.asList(environment.getActiveProfiles()).contains("dev")) {
//            initData();
//        }
//    }

//    private void initData() {
//        for (int i = 0; i < 114; i++) {
//            Test test = Test.builder()
//                    .title("TEST TITLE " + i)
//                    .content("TEST CONTENT " + i)
//                    .createdBy("admin")
//                    .deleted(false)
//                    .notice(false)
//                    .build();
//            testRepository.save(test);
//        }
//    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody TestRequest request) {
        Long id = testService.save(request);
        return new ResponseEntity<>(CommonApiResponse.success(id), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> search(TestSearch search, Pageable pageable, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Page<TestListResponse> page = testService.search(search, pageable);
        return ResponseEntity.ok(CommonApiResponse.success(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        TestResponse response = testService.getTest(id);
        return ResponseEntity.ok(CommonApiResponse.success(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody TestRequest request) {
        TestResponse response = testService.edit(id, request);
        return ResponseEntity.ok(CommonApiResponse.success(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @AuthenticationPrincipal Principal principal) {
        testService.delete(id);
        return ResponseEntity.ok(CommonApiResponse.success(id));
    }

}
