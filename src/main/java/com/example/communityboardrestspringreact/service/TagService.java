package com.example.communityboardrestspringreact.service;

import com.example.communityboardrestspringreact.web.dto.response.TagResponse;

import java.util.List;

public interface TagService {
    List<TagResponse> getByCommunity(Long id);
}
