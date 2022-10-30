package com.example.communityboardrestspringreact.web.dto.response.account;

import com.example.communityboardrestspringreact.web.dto.response.role.RoleCodeResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AccountSearchResponse {

    private int rowNum;
    private Long id;
    private String accountToken;
    private String name;
    private String email;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime joinedAt;
    private String profileImage;
    private List<RoleCodeResponse> roles = new ArrayList<>();

}
