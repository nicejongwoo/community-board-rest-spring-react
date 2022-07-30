package com.example.communityboardrestspringreact.security.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenResponse {

    private String accessToken;
    private String refreshToken;

}
