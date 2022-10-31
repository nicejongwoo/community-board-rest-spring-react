package com.example.communityboardrestspringreact.repository;

import com.example.communityboardrestspringreact.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
