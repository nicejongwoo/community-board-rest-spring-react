package com.example.communityboardrestspringreact.web.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CommunityResponse extends BaseResponse{

    private Long id;
    private String title;
    private String content;
    private List<TagResponse> tags;
    private List<AnswerResponse> answers;

}
