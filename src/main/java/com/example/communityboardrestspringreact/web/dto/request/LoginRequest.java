package com.example.communityboardrestspringreact.web.dto.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class LoginRequest {

    private String email;
    private String password;

}
