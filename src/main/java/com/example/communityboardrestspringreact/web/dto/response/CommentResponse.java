package com.example.communityboardrestspringreact.web.dto.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentResponse {
    private Long id;
    private String content;
}
