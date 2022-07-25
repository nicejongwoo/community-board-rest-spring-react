package com.example.communityboardrestspringreact.web.dto.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CategoryResponse {

    private Long id;
    private String name;
    private String type;
}
