package com.example.communityboardrestspringreact.service.impl;

import com.example.communityboardrestspringreact.repository.TagRepository;
import com.example.communityboardrestspringreact.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

}
