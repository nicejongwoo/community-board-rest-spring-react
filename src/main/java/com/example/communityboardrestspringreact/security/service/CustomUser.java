package com.example.communityboardrestspringreact.security.service;

import com.example.communityboardrestspringreact.domain.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {

    private final Account account;

    public CustomUser(Account account, Collection<? extends GrantedAuthority> authorities) {
        super(
                account.getEmail(), account.getPassword(), authorities
        );
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}