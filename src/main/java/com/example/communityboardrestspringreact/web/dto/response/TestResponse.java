package com.example.communityboardrestspringreact.web.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestResponse {

    private Long id;

    private String title;

    private String content;

    private Boolean notice;

    private Boolean deleted;

    private String createdBy;

    private LocalDateTime createdAt;

}
