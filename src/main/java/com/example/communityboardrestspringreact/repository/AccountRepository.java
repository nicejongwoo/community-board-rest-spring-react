package com.example.communityboardrestspringreact.repository;

import com.example.communityboardrestspringreact.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByEmail(String email);

    Optional<Account> findByEmail(String email);

    Optional<Account> findByAccountToken(String token);
}
