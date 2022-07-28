package com.example.communityboardrestspringreact.web.dto.response;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CommunityResponse {

    private Long id;
    private String title;
    private String content;
    private List<TagResponse> tags;
    private List<AnswerResponse> answers;

}
