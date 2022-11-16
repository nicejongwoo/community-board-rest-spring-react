package com.example.communityboardrestspringreact.web.dto.request.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountEditRequest {

    private String name;
    private String email;
    private String phone;
    private String password;
    private String profileImage;

}
