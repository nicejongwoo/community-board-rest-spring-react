package com.example.communityboardrestspringreact.domain;

import java.time.LocalDateTime;

public class BaseEntity {

    private String createdBy = "user";
    private LocalDateTime createdAt = LocalDateTime.now();

    private String updatedBy = "user";
    private LocalDateTime updatedAt = LocalDateTime.now();

}
