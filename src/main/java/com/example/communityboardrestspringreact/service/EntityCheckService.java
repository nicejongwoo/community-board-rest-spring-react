package com.example.communityboardrestspringreact.service;

import com.example.communityboardrestspringreact.domain.Account;
import com.example.communityboardrestspringreact.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Slf4j
@Service
public class EntityCheckService {
    private final AccountRepository accountRepository;

    public Account checkAccountByToken(String token) {
        return accountRepository.findByAccountToken(token).orElseThrow(() -> new EntityNotFoundException("Not Found Account By Token"));
    }
}
