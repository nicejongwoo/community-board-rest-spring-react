package com.example.communityboardrestspringreact.web.dto.response;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerResponse {
    private Long id;
    private String content;
    private List<CommentResponse> comments;
}
