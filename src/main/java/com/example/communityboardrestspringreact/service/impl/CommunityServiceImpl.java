package com.example.communityboardrestspringreact.service.impl;

import com.example.communityboardrestspringreact.domain.Category;
import com.example.communityboardrestspringreact.domain.Community;
import com.example.communityboardrestspringreact.domain.Tag;
import com.example.communityboardrestspringreact.repository.CategoryRepository;
import com.example.communityboardrestspringreact.repository.CommunityRepository;
import com.example.communityboardrestspringreact.repository.TagRepository;
import com.example.communityboardrestspringreact.service.CommunityService;
import com.example.communityboardrestspringreact.web.dto.mapper.CommunityDtoMapper;
import com.example.communityboardrestspringreact.web.dto.mapper.TagDtoMapper;
import com.example.communityboardrestspringreact.web.dto.request.CommunityRequest;
import com.example.communityboardrestspringreact.web.dto.request.TagRequest;
import com.example.communityboardrestspringreact.web.dto.response.CommunityResponse;
import com.example.communityboardrestspringreact.web.dto.search.CommunitySearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public Long register(CommunityRequest request) {
        Community community = CommunityDtoMapper.MAPPER.toEntity(request);
        Category category = checkCategory(request.getCategoryId());
        String[] tags = request.getTags();

        communityRepository.save(community);
        community.updateCategory(category);
        saveTags(community, tags);

        return community.getId();
    }

    @Override
    public Page<CommunityResponse> search(CommunitySearch search, Pageable pageable) {
        return null;
    }

    @Override
    public CommunityResponse getOne(Long id) {
        Community community = checkCommunity(id);
        return CommunityDtoMapper.MAPPER.toDto(community);
    }

    @Transactional
    @Override
    public void edit(Long id, CommunityRequest request) {
        Community community = checkCommunity(id);
        Category category = checkCategory(request.getCategoryId());

        String[] tags = request.getTags();
        //기존의 tag는 모두 지우고 다시 새로 등록하는 전략
        Long tagCount = tagRepository.countByCommunityId(id);
        if (tagCount > 0) {
            tagRepository.deleteAllByCommunityId(id);
        }

        community.update(request.getTitle(), request.getContent());
        community.updateCategory(category);
        saveTags(community, tags);
    }

    @Override
    public void delete(Long id) {
        Community community = checkCommunity(id);
        communityRepository.delete(community);
    }

    private void saveTags(Community community, String[] tags) {
        if (tags != null && tags.length > 0) {
            for (String tagName : tags) {
                if (StringUtils.hasText(tagName)) {
                    TagRequest tagRequest = TagRequest.builder().name(tagName).build();
                    Tag tag = TagDtoMapper.MAPPER.toEntity(tagRequest);
                    tag.updateCommunity(community);
                    tagRepository.save(tag);
                }
            }
        }
    }

    private Category checkCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not Found Category")
        );
    }

    private Community checkCommunity(Long id) {
        return communityRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not Found Community")
        );
    }
}
