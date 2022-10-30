package com.example.communityboardrestspringreact.web.dto.response.role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleCodeResponse {

    private String code;
    private String name;

    public RoleCodeResponse(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
