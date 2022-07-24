package com.example.communityboardrestspringreact.service.impl;

import com.example.communityboardrestspringreact.repository.CommentRepository;
import com.example.communityboardrestspringreact.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

}
