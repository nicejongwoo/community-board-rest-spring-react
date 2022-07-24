package com.example.communityboardrestspringreact.service.impl;

import com.example.communityboardrestspringreact.repository.CommentRepository;
import com.example.communityboardrestspringreact.service.CommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommunityServiceImpl implements CommunityService {

    private final CommentRepository commentRepository;

}
