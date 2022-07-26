package com.example.communityboardrestspringreact.service.impl;

import com.example.communityboardrestspringreact.domain.Account;
import com.example.communityboardrestspringreact.domain.Role;
import com.example.communityboardrestspringreact.repository.AccountRepository;
import com.example.communityboardrestspringreact.repository.RoleRepository;
import com.example.communityboardrestspringreact.service.AccountService;
import com.example.communityboardrestspringreact.web.dto.mapper.AccountDtoMapper;
import com.example.communityboardrestspringreact.web.dto.request.account.AccountEditRequest;
import com.example.communityboardrestspringreact.web.dto.request.account.AccountRegisterRequest;
import com.example.communityboardrestspringreact.web.dto.response.account.AccountResponse;
import com.example.communityboardrestspringreact.web.dto.response.account.AccountSearchResponse;
import com.example.communityboardrestspringreact.web.dto.response.role.RoleCodeResponse;
import com.example.communityboardrestspringreact.web.dto.search.account.AccountSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final String INIT_PASSWORD = "password123!@#";

    @Transactional
    @Override
    public String register(AccountRegisterRequest request) {
        Account account = AccountDtoMapper.MAPPER.register(request);
        Role role = roleRepository.findByRole("ROLE_USER").get();

        account.updateEncodedPassword(passwordEncoder.encode(INIT_PASSWORD));
        account.setRoles(Arrays.asList(role));

        accountRepository.save(account);
        return account.getAccountToken();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<AccountSearchResponse> search(AccountSearch search, Pageable pageable) {
        Page<AccountSearchResponse> page = accountRepository.search(search, pageable);
        long totalElements = page.getTotalElements();

        IntStream.range(0, (int) totalElements).forEach(index -> {
            AccountSearchResponse data = page.getContent().get(index);
            data.setRowNum(index + 1);

            Account account = accountRepository.findByAccountToken(data.getAccountToken()).get();
            List<Role> roles = account.getRoles();

            data.setRoles(roles.stream().map(role -> {
                return new RoleCodeResponse(role.getCode(), role.getName());
            }).collect(Collectors.toList()));

        });

        return page;
    }

    @Transactional(readOnly = true)
    @Override
    public AccountResponse getOne(String token) {
        Account account = getAccount(token);
        return AccountDtoMapper.MAPPER.getOne(account);
    }

    @Transactional
    @Override
    public void edit(String token, AccountEditRequest request) {
        Account account = getAccount(token);
        account.edit(request);
        account.updateEncodedPassword(request.getPassword());
    }

    @Transactional
    @Override
    public void delete(String token) {
        Account account = getAccount(token);
        accountRepository.delete(account);
    }

    private Account getAccount(String token) {
        Account account = accountRepository.findByAccountToken(token)
                .orElseThrow(() -> new RuntimeException("Not Found Account"));
        return account;
    }
}
