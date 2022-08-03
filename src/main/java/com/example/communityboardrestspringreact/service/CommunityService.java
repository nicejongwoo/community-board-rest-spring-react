package com.example.communityboardrestspringreact.service;

import com.example.communityboardrestspringreact.domain.Community;
import com.example.communityboardrestspringreact.web.dto.request.CommunityRequest;
import com.example.communityboardrestspringreact.web.dto.response.CommunityListResponse;
import com.example.communityboardrestspringreact.web.dto.response.CommunityResponse;
import com.example.communityboardrestspringreact.web.dto.search.CommunitySearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommunityService {
    Long register(CommunityRequest request);

    Page<CommunityListResponse> search(CommunitySearch search, Pageable pageable);

    CommunityResponse getOne(Long id);

    void edit(CommunityRequest request, Community community);

    void delete(Community community);

    Community checkCommunity(Long id);

}
