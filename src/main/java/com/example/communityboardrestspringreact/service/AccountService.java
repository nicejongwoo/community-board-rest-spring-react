package com.example.communityboardrestspringreact.service;

import com.example.communityboardrestspringreact.web.dto.request.account.AccountEditRequest;
import com.example.communityboardrestspringreact.web.dto.request.account.AccountRegisterRequest;
import com.example.communityboardrestspringreact.web.dto.response.account.AccountResponse;
import com.example.communityboardrestspringreact.web.dto.response.account.AccountSearchResponse;
import com.example.communityboardrestspringreact.web.dto.search.account.AccountSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {
    Long register(AccountRegisterRequest request);

    Page<AccountSearchResponse> search(AccountSearch search, Pageable pageable);

    AccountResponse getOne(String token);

    void edit(String token, AccountEditRequest request);

    void delete(String token);
}
