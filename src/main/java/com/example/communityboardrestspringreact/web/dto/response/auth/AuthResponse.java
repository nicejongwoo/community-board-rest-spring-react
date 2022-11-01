package com.example.communityboardrestspringreact.web.dto.response.auth;

import com.example.communityboardrestspringreact.web.dto.response.role.RoleCodeResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthResponse {

    private String accountToken;
    private String name;
    private String email;
    private String phone;
    private String profileImage;

    private List<RoleCodeResponse> roles = new ArrayList<>();

    private String token;
    private String refresh;
    private LocalDateTime expiryDate;

}
