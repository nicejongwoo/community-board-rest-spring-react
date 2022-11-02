package com.example.communityboardrestspringreact.web.dto.response.auth;

import com.example.communityboardrestspringreact.web.dto.response.account.AccountResponse;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthResponse {

    private AccountResponse auth;

}
