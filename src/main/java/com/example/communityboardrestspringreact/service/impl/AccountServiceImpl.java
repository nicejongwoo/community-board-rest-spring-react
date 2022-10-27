package com.example.communityboardrestspringreact.service.impl;

import com.example.communityboardrestspringreact.domain.Account;
import com.example.communityboardrestspringreact.repository.AccountRepository;
import com.example.communityboardrestspringreact.service.AccountService;
import com.example.communityboardrestspringreact.web.dto.mapper.AccountDtoMapper;
import com.example.communityboardrestspringreact.web.dto.request.account.AccountRegisterRequest;
import com.example.communityboardrestspringreact.web.dto.response.account.AccountSearchResponse;
import com.example.communityboardrestspringreact.web.dto.response.account.AccountResponse;
import com.example.communityboardrestspringreact.web.dto.search.account.AccountSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Long register(AccountRegisterRequest request) {
        Account account = AccountDtoMapper.MAPPER.register(request);
        account.updateEncodedPassword(passwordEncoder.encode(request.getPassword()));

        accountRepository.save(account);
        return account.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<AccountSearchResponse> search(AccountSearch search, Pageable pageable) {
        return accountRepository.search(search, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public AccountResponse getOne(String token) {
        Account account = accountRepository.findByAccountToken(token)
                .orElseThrow(() -> new RuntimeException("Not Found Account"));

        return AccountDtoMapper.MAPPER.getOne(account);
    }
}
