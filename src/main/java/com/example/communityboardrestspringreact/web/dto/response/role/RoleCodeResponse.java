package com.example.communityboardrestspringreact.web.dto.response.role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleCodeResponse {

    private String code;

    public RoleCodeResponse(String code) {
        this.code = code;
    }
}
