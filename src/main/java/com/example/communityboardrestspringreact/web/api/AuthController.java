package com.example.communityboardrestspringreact.web.api;

import com.example.communityboardrestspringreact.domain.Account;
import com.example.communityboardrestspringreact.domain.Role;
import com.example.communityboardrestspringreact.repository.AccountRepository;
import com.example.communityboardrestspringreact.repository.RoleRepository;
import com.example.communityboardrestspringreact.security.service.JwtTokenService;
import com.example.communityboardrestspringreact.security.web.dto.response.TokenResponse;
import com.example.communityboardrestspringreact.web.dto.mapper.AccountDtoMapper;
import com.example.communityboardrestspringreact.web.dto.request.LoginRequest;
import com.example.communityboardrestspringreact.web.dto.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final JwtTokenService tokenService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignUpRequest request) {

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        TokenResponse tokenResponse = tokenService.generateToken(authenticate);
        return ResponseEntity.ok(tokenResponse);
    }

}
