package com.example.communityboardrestspringreact.web.dto.request.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRegisterRequest {

    private String name;
    private String email;
    private String phone;

}
