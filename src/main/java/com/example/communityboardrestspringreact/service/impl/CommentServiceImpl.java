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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AnswerRepository answerRepository;

    @Transactional
    @Override
    public Long register(CommentRequest request) {
        Comment comment = CommentDtoMapper.MAPPER.toEntity(request);

        Answer answer = answerRepository.findById(request.getAnswerId()).orElseThrow(() -> new RuntimeException("Not Found Answer"));

        comment.updateAnswer(answer);
        commentRepository.save(comment);
        return comment.getId();
    }

    @Override
    public Page<CommentResponse> search(CommentSearch search, Pageable pageable) {
        return null;
    }

    @Override
    public CommentResponse getOne(Long id) {
        return null;
    }

    @Transactional
    @Override
    public void edit(Long id, CommentRequest request) {
        Comment comment = checkComment(id);
        comment.update(request.getContent());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Comment comment = checkComment(id);
        commentRepository.delete(comment);
    }

    private Comment checkComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found Comment"));
    }
}
