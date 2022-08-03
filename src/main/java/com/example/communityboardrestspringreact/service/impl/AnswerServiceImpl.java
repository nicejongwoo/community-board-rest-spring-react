package com.example.communityboardrestspringreact.service.impl;

import com.example.communityboardrestspringreact.domain.Answer;
import com.example.communityboardrestspringreact.domain.Community;
import com.example.communityboardrestspringreact.repository.AnswerRepository;
import com.example.communityboardrestspringreact.repository.CommunityRepository;
import com.example.communityboardrestspringreact.service.AnswerService;
import com.example.communityboardrestspringreact.web.dto.mapper.AnswerDtoMapper;
import com.example.communityboardrestspringreact.web.dto.request.AnswerRequest;
import com.example.communityboardrestspringreact.web.dto.response.AnswerResponse;
import com.example.communityboardrestspringreact.web.dto.search.AnswerSearch;
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
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final CommunityRepository communityRepository;

    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    @Override
    public Long register(AnswerRequest request) {
        Answer answer = AnswerDtoMapper.MAPPER.toEntity(request);
        Community community = checkCommunity(request);

        answerRepository.save(answer);
        answer.updateCommunity(community);
        return answer.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<AnswerResponse> search(AnswerSearch search, Pageable pageable) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public AnswerResponse getOne(Long id) {
        return null;
    }

    @PreAuthorize("#answer.createdBy == authentication.name OR hasRole('ROLE_ADMIN')")
    @Transactional
    @Override
    public void edit(AnswerRequest request, Answer answer) {
        answer.update(request.getContent());
    }

    @PreAuthorize("#answer.createdBy == authentication.name OR hasRole('ROLE_ADMIN')")
    @Transactional
    @Override
    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }

    @Override
    public Answer checkAnswer(Long id) {
        return answerRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found Answer"));
    }

    private Community checkCommunity(AnswerRequest request) {
        return communityRepository.findById(request.getCommunityId()).orElseThrow(() -> new RuntimeException("Not Found Community"));
    }

}
