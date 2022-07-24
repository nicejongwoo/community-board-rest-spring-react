package com.example.communityboardrestspringreact.service.impl;

import com.example.communityboardrestspringreact.repository.AnswerRepository;
import com.example.communityboardrestspringreact.service.AnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

}
