package com.example.communityboardrestspringreact.web.api;

import com.example.communityboardrestspringreact.domain.Account;
import com.example.communityboardrestspringreact.domain.Role;
import com.example.communityboardrestspringreact.repository.AccountRepository;
import com.example.communityboardrestspringreact.repository.RoleRepository;
import com.example.communityboardrestspringreact.web.dto.mapper.AccountDtoMapper;
import com.example.communityboardrestspringreact.web.dto.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignUpRequest request) {

        if (accountRepository.existsByUsername(request.getUsername())) {
            return new ResponseEntity<>("Username is already taken: " + request.getUsername(), HttpStatus.BAD_REQUEST);
        }

        if (accountRepository.existsByEmail(request.getEmail())) {
            return new ResponseEntity<>("Email is already taken: " + request.getEmail(), HttpStatus.BAD_REQUEST);
        }

        //request to entity
        Account account = AccountDtoMapper.MAPPER.toEntity(request);
        account.encryptPassword(passwordEncoder.encode(request.getPassword()));

        Role roles = roleRepository.findByName("ROLE_USER").get();
        account.setRoles(Collections.singleton(roles));

        accountRepository.save(account);

        return new ResponseEntity<>(account.getId(), HttpStatus.CREATED);
    }
}
