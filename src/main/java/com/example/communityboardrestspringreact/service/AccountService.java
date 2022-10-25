package com.example.communityboardrestspringreact.service;

import com.example.communityboardrestspringreact.web.dto.request.account.AccountRegisterRequest;
import com.example.communityboardrestspringreact.web.dto.response.account.AccountListResponse;
import com.example.communityboardrestspringreact.web.dto.response.account.AccountResponse;
import com.example.communityboardrestspringreact.web.dto.search.account.AccountSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {
    Long register(AccountRegisterRequest request);

    Page<AccountListResponse> search(AccountSearch search, Pageable pageable);

    AccountResponse getOne(Long id);

}
