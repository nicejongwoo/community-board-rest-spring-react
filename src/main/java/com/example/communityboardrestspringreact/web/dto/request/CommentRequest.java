package com.example.communityboardrestspringreact.web.dto.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CommentRequest {
    private String content;
    private Long answerId;
}
