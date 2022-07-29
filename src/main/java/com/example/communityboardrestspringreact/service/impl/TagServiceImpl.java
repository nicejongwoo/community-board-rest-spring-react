package com.example.communityboardrestspringreact.service.impl;

import com.example.communityboardrestspringreact.domain.Community;
import com.example.communityboardrestspringreact.domain.Tag;
import com.example.communityboardrestspringreact.repository.CommunityRepository;
import com.example.communityboardrestspringreact.repository.TagRepository;
import com.example.communityboardrestspringreact.service.TagService;
import com.example.communityboardrestspringreact.web.dto.mapper.TagDtoMapper;
import com.example.communityboardrestspringreact.web.dto.response.TagResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final CommunityRepository communityRepository;

    @Override
    public List<TagResponse> getByCommunity(Long id) {
        Community community = communityRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not Found Community")
        );

        List<Tag> tag = tagRepository.findAllByCommunityId(community.getId());

        return TagDtoMapper.MAPPER.toDtoList(tag);
    }
}
