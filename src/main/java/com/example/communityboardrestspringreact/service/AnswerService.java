package com.example.communityboardrestspringreact.service;

import com.example.communityboardrestspringreact.domain.Answer;
import com.example.communityboardrestspringreact.web.dto.request.AnswerRequest;
import com.example.communityboardrestspringreact.web.dto.response.AnswerResponse;
import com.example.communityboardrestspringreact.web.dto.search.AnswerSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnswerService {
    Long register(AnswerRequest request);

    Page<AnswerResponse> search(AnswerSearch search, Pageable pageable);

    AnswerResponse getOne(Long id);

    void edit(AnswerRequest request, Answer answer);

    void delete(Answer answer);

    Answer checkAnswer(Long id);
}
