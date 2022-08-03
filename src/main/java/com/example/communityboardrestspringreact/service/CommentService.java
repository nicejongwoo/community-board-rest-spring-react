package com.example.communityboardrestspringreact.service;

import com.example.communityboardrestspringreact.domain.Comment;
import com.example.communityboardrestspringreact.web.dto.request.CommentRequest;
import com.example.communityboardrestspringreact.web.dto.response.CommentResponse;
import com.example.communityboardrestspringreact.web.dto.search.CommentSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    Long register(CommentRequest request);

    Page<CommentResponse> search(CommentSearch search, Pageable pageable);

    CommentResponse getOne(Long id);

    void edit(CommentRequest request, Comment comment);

    void delete(Comment comment);

    Comment checkComment(Long id);
}
