package com.example.communityboardrestspringreact.service.impl;

import com.example.communityboardrestspringreact.repository.AccountRepository;
import com.example.communityboardrestspringreact.service.AccountService;
import com.example.communityboardrestspringreact.web.dto.request.account.AccountRegisterRequest;
import com.example.communityboardrestspringreact.web.dto.response.account.AccountListResponse;
import com.example.communityboardrestspringreact.web.dto.response.account.AccountResponse;
import com.example.communityboardrestspringreact.web.dto.search.account.AccountSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Long register(AccountRegisterRequest request) {
        return null;
    }

    @Override
    public Page<AccountListResponse> search(AccountSearch search, Pageable pageable) {
        return null;
    }

    @Override
    public AccountResponse getOne(Long id) {
        return null;
    }
}
