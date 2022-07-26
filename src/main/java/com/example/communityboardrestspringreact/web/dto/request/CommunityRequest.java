package com.example.communityboardrestspringreact.web.dto.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CommunityRequest {
    private String title;
    private String content;
    private String[] tags;
    private Long categoryId;
}
