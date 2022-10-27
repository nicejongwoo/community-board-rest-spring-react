package com.example.communityboardrestspringreact.web.dto.response.account;

import com.example.communityboardrestspringreact.web.dto.response.role.RoleCodeResponse;
import lombok.*;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountResponse {

    private String accountToken;
    private String name;
    private String email;
    private String phone;
    private String profileImage;

    private Set<RoleCodeResponse> roles;

}
