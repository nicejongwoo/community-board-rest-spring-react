package com.example.communityboardrestspringreact.repository.custom;

import com.example.communityboardrestspringreact.web.dto.response.account.AccountSearchResponse;
import com.example.communityboardrestspringreact.web.dto.search.account.AccountSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountRepositoryCustom {
    Page<AccountSearchResponse> search(AccountSearch search, Pageable pageable);
}
