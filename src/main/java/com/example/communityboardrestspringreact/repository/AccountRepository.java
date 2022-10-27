package com.example.communityboardrestspringreact.repository;

import com.example.communityboardrestspringreact.domain.Account;
import com.example.communityboardrestspringreact.repository.custom.AccountRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryCustom {

    boolean existsByEmail(String email);

    Optional<Account> findByEmail(String email);

    Optional<Account> findByAccountToken(String token);
}
