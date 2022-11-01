package com.example.communityboardrestspringreact.repository;

import com.example.communityboardrestspringreact.domain.RefreshToken;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    @Modifying
    void deleteByToken(@NotNull String cookieValue);
}
