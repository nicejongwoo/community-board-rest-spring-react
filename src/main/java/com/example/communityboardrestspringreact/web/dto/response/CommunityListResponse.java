package com.example.communityboardrestspringreact.web.dto.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CommunityListResponse {
    private Long id;
    private String title;
    private String content;
    private Long answerCount;
}
