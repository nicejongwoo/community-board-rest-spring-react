package com.example.communityboardrestspringreact.web.api;

import com.example.communityboardrestspringreact.domain.Account;
import com.example.communityboardrestspringreact.domain.Role;
import com.example.communityboardrestspringreact.repository.AccountRepository;
import com.example.communityboardrestspringreact.repository.RoleRepository;
import com.example.communityboardrestspringreact.security.service.CustomUserDetails;
import com.example.communityboardrestspringreact.security.service.JwtTokenService;
import com.example.communityboardrestspringreact.security.web.dto.response.TokenResponse;
import com.example.communityboardrestspringreact.web.dto.mapper.AccountDtoMapper;
import com.example.communityboardrestspringreact.web.dto.request.LoginRequest;
import com.example.communityboardrestspringreact.web.dto.request.RoleRequest;
import com.example.communityboardrestspringreact.web.dto.request.SignUpRequest;
import com.example.communityboardrestspringreact.web.dto.response.AccountResponse;
import com.example.communityboardrestspringreact.web.dto.response.CommonApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Transactional
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignUpRequest request) {

        if (accountRepository.existsByEmail(request.getEmail())) {
            return new ResponseEntity<>("Email is already taken: " + request.getEmail(), HttpStatus.BAD_REQUEST);
        }

        Role role = roleRepository.findByRole("ROLE_USER").get();

        Account account = AccountDtoMapper.MAPPER.toEntity(request);
        account.updateEncodedPassword(passwordEncoder.encode(request.getPassword()));
        account.setRoles(Arrays.asList(role));

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

        CustomUserDetails userDetails = (CustomUserDetails) authenticate.getPrincipal();
        AccountResponse accountResponse = AccountResponse.builder()
                .token(tokenResponse)
                .account(userDetails.getAccount())
                .build();

        return ResponseEntity.ok(CommonApiResponse.success(accountResponse));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@AuthenticationPrincipal CustomUserDetails principal) {
        log.debug("principal:: " + principal);
        SecurityContextHolder.clearContext();

        return ResponseEntity.ok(CommonApiResponse.success(null));
    }

    @Transactional
    @PutMapping("/{id}/roles")
    public ResponseEntity<?> giveRoles(@PathVariable Long id, @RequestBody RoleRequest request) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("찾으시는 계정이 없습니다."));

        List<Role> currentRoles = account.getRoles();
        List<Role> newRoles = new ArrayList<>();

        request.getRoles().forEach(roleName -> {
            newRoles.add(roleRepository.findByName(roleName).get());
        });
        newRoles.addAll(currentRoles);

        account.setRoles(newRoles);

        return new ResponseEntity<>(CommonApiResponse.success(account.getId(), "권한을 추가하였습니다."), HttpStatus.OK);
    }

}
