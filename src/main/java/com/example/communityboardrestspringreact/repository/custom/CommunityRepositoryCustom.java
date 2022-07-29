package com.example.communityboardrestspringreact.repository.custom;

import com.example.communityboardrestspringreact.web.dto.response.CommunityListResponse;
import com.example.communityboardrestspringreact.web.dto.search.CommunitySearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommunityRepositoryCustom {
    Page<CommunityListResponse> search(CommunitySearch search, Pageable pageable);
}
