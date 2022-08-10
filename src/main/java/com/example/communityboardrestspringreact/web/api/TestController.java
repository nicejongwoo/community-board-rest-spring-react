package com.example.communityboardrestspringreact.web.api;

import com.example.communityboardrestspringreact.domain.Test;
import com.example.communityboardrestspringreact.repository.TestRepository;
import com.example.communityboardrestspringreact.web.dto.request.TestRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/test")
@RestController
public class TestController {

    private final TestRepository testRepository;

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody TestRequest test) {
        Test build = Test.builder().useEnabled(false).useYn(true).build();
        Test save = testRepository.save(build);
        return ResponseEntity.ok(save);
    }

    @GetMapping("")
    public ResponseEntity<?> getList() {
        return ResponseEntity.ok(testRepository.findAll());
    }

}
