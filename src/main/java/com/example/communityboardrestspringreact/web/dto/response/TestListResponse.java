package com.example.communityboardrestspringreact.web.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TestListResponse {

    private Long id;

    private String title;

    private String content;

    private Boolean notice;

    private Boolean deleted;

    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

}
