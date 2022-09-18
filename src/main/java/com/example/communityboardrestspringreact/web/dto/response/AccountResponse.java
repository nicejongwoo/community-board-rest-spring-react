package com.example.communityboardrestspringreact.web.dto.response;

import com.example.communityboardrestspringreact.domain.Account;
import com.example.communityboardrestspringreact.security.web.dto.response.TokenResponse;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountResponse {

    private TokenResponse token;
//    private AccountSecurityAdapter user;
    private Account account;

}
