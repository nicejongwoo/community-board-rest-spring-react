package com.example.communityboardrestspringreact.web.dto.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class SignUpRequest {

    private String name;
    private String email;
    private String password;

}
