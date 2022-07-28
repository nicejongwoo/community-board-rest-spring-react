package com.example.communityboardrestspringreact.web.dto.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TagResponse {
    private Long id;
    private String name;
}
