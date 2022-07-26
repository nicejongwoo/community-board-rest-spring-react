package com.example.communityboardrestspringreact.web.api;

import com.example.communityboardrestspringreact.domain.Comment;
import com.example.communityboardrestspringreact.service.CommentService;
import com.example.communityboardrestspringreact.web.dto.request.CommentRequest;
import com.example.communityboardrestspringreact.web.dto.response.CommentResponse;
import com.example.communityboardrestspringreact.web.dto.response.CommonApiResponse;
import com.example.communityboardrestspringreact.web.dto.search.CommentSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/comment")
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<?> register(@RequestBody CommentRequest request) {
        Long id = commentService.register(request);
        return new ResponseEntity<>(CommonApiResponse.success(id, "등록 되었습니다."), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> search(CommentSearch search, Pageable pageable) {
        Page<CommentResponse> page = commentService.search(search, pageable);
        return new ResponseEntity<>(CommonApiResponse.success(page), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        CommentResponse response = commentService.getOne(id);
        return new ResponseEntity<>(CommonApiResponse.success(response), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody CommentRequest request) {
        Comment comment = commentService.checkComment(id);
        commentService.edit(request, comment);
        return new ResponseEntity<>(CommonApiResponse.success(id, "수정 되었습니다."), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Comment comment = commentService.checkComment(id);
        commentService.delete(comment);
        return new ResponseEntity<>(CommonApiResponse.success(id, "삭제 되었습니다."), HttpStatus.OK);
    }
}
