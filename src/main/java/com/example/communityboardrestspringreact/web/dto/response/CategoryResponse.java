package com.example.communityboardrestspringreact.web.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CategoryResponse extends BaseResponse {

    private Long id;
    private String name;
    private String type;

}
