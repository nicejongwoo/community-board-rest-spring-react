package com.example.communityboardrestspringreact.web.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TestRequest {

    private Long id;

    private String title;

    private String content;

    private Boolean notice;

    private Boolean deleted;

    private String createdBy;

    private LocalDateTime createdAt;

}
