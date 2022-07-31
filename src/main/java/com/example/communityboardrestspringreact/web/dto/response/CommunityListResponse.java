package com.example.communityboardrestspringreact.web.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CommunityListResponse extends BaseResponse{
    private Long id;
    private String title;
    private String content;
    private Long answerCount;
}
