package com.example.communityboardrestspringreact.web.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerResponse extends BaseResponse {
    private Long id;
    private String content;
    private List<CommentResponse> comments;
}
