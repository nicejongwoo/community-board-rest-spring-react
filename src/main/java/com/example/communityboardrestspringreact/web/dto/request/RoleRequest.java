package com.example.communityboardrestspringreact.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleRequest {

    private Set<String> roles;

}
