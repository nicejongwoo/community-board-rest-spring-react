package com.example.communityboardrestspringreact.web.dto.response;

import lombok.Data;

@Data
public class TestListResponse {
    private Long id;
    private String content;
    private Boolean useYn;
    private Boolean useEnabled;
}
