package com.example.communityboardrestspringreact.service.impl;

import com.example.communityboardrestspringreact.domain.RefreshToken;
import com.example.communityboardrestspringreact.repository.AccountRepository;
import com.example.communityboardrestspringreact.repository.RefreshTokenRepository;
import com.example.communityboardrestspringreact.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${jwt.refreshExpirationMs}")
    private Long REFRESH_TOKEN_PERIOD;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AccountRepository accountRepository;

    @Transactional
    @Override
    public RefreshToken createRefreshToken(String accountToken) {

        RefreshToken refreshToken = RefreshToken.builder()
                .account(accountRepository.findByAccountToken(accountToken).get())
                .expiryDate(LocalDateTime.now().plusSeconds(REFRESH_TOKEN_PERIOD / 1000))
                .token(UUID.randomUUID().toString())
                .build();

        refreshTokenRepository.save(refreshToken);

        return refreshToken;
    }

    @Transactional
    @Override
    public void deleteByToken(String cookieValue) {
        refreshTokenRepository.deleteByToken(cookieValue);
    }
}
