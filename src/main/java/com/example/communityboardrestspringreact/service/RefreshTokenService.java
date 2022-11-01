package com.example.communityboardrestspringreact.service;

import com.example.communityboardrestspringreact.domain.RefreshToken;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(String accountToken);
}
