package com.example.communityboardrestspringreact.web.api;

import com.example.communityboardrestspringreact.domain.Answer;
import com.example.communityboardrestspringreact.service.AnswerService;
import com.example.communityboardrestspringreact.web.dto.request.AnswerRequest;
import com.example.communityboardrestspringreact.web.dto.response.AnswerResponse;
import com.example.communityboardrestspringreact.web.dto.response.CommonApiResponse;
import com.example.communityboardrestspringreact.web.dto.search.AnswerSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/answer")
@RestController
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("")
    public ResponseEntity<?> register(@RequestBody AnswerRequest request) {
        Long id = answerService.register(request);
        return new ResponseEntity<>(CommonApiResponse.success(id, "등록 되었습니다."), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> search(AnswerSearch search, Pageable pageable) {
        Page<AnswerResponse> page = answerService.search(search, pageable);
        return new ResponseEntity<>(CommonApiResponse.success(page), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        AnswerResponse response = answerService.getOne(id);
        return new ResponseEntity<>(CommonApiResponse.success(response), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody AnswerRequest request) {
        Answer answer = answerService.checkAnswer(id);
        answerService.edit(request, answer);
        return new ResponseEntity<>(CommonApiResponse.success(id, "수정 되었습니다."), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Answer answer = answerService.checkAnswer(id);
        answerService.delete(answer);
        return new ResponseEntity<>(CommonApiResponse.success(id, "삭제 되었습니다."), HttpStatus.OK);
    }
}
