package com.example.communityboardrestspringreact.service.impl;

import com.example.communityboardrestspringreact.domain.Answer;
import com.example.communityboardrestspringreact.domain.Comment;
import com.example.communityboardrestspringreact.repository.AnswerRepository;
import com.example.communityboardrestspringreact.repository.CommentRepository;
import com.example.communityboardrestspringreact.service.CommentService;
import com.example.communityboardrestspringreact.web.dto.mapper.CommentDtoMapper;
import com.example.communityboardrestspringreact.web.dto.request.CommentRequest;
import com.example.communityboardrestspringreact.web.dto.response.CommentResponse;
import com.example.communityboardrestspringreact.web.dto.search.CommentSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AnswerRepository answerRepository;

    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    @Override
    public Long register(CommentRequest request) {
        Comment comment = CommentDtoMapper.MAPPER.toEntity(request);

        Answer answer = answerRepository.findById(request.getAnswerId()).orElseThrow(() -> new RuntimeException("Not Found Answer"));

        comment.updateAnswer(answer);
        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CommentResponse> search(CommentSearch search, Pageable pageable) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public CommentResponse getOne(Long id) {
        return null;
    }

    @PreAuthorize("#comment.createdBy == authentication.name OR hasRole('ROLE_ADMIN')")
    @Transactional
    @Override
    public void edit(CommentRequest request, Comment comment) {
        comment.update(request.getContent());
    }

    @PreAuthorize("#comment.createdBy == authentication.name OR hasRole('ROLE_ADMIN')")
    @Transactional
    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public Comment checkComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found Comment"));
    }
}
