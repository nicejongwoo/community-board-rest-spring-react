package com.example.communityboardrestspringreact.web.api;

import com.example.communityboardrestspringreact.domain.Account;
import com.example.communityboardrestspringreact.domain.RefreshToken;
import com.example.communityboardrestspringreact.domain.Role;
import com.example.communityboardrestspringreact.repository.AccountRepository;
import com.example.communityboardrestspringreact.repository.RoleRepository;
import com.example.communityboardrestspringreact.security.service.CustomUserDetails;
import com.example.communityboardrestspringreact.security.service.JwtTokenService;
import com.example.communityboardrestspringreact.service.RefreshTokenService;
import com.example.communityboardrestspringreact.util.ResponseCookieGenerator;
import com.example.communityboardrestspringreact.web.dto.mapper.AccountDtoMapper;
import com.example.communityboardrestspringreact.web.dto.request.LoginRequest;
import com.example.communityboardrestspringreact.web.dto.request.RoleRequest;
import com.example.communityboardrestspringreact.web.dto.request.SignUpRequest;
import com.example.communityboardrestspringreact.web.dto.response.CommonApiResponse;
import com.example.communityboardrestspringreact.web.dto.response.account.AccountResponse;
import com.example.communityboardrestspringreact.web.dto.response.auth.AuthResponse;
import com.example.communityboardrestspringreact.web.dto.response.role.RoleCodeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService tokenService;
    private final RefreshTokenService refreshTokenService;
    @Value("${jwt.refreshExpirationMs}")
    private Long REFRESH_TOKEN_PERIOD;

    @Value("${jwt.accessExpirationsMs}")
    private Long TOKEN_PERIOD;

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

        //authenticationToken
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        //setAuthentication
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        //userDetails
        CustomUserDetails userDetails = (CustomUserDetails) authenticate.getPrincipal();
        Account account = userDetails.getAccount();
        List<RoleCodeResponse> roleCodeResponses = account.getRoles().stream().map(role -> {
            return new RoleCodeResponse(role.getCode(), role.getName());
        }).collect(Collectors.toList());

        //token
        String accessToken = tokenService.createAccessToken(authenticate);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(account.getAccountToken());

        AccountResponse accountResponse = AccountResponse.builder()
                .accountToken(account.getAccountToken())
                .email(account.getEmail())
                .name(account.getName())
                .phone(account.getPhone())
                .profileImage(account.getProfileImage())
                .roles(roleCodeResponses)
                .build();

        AuthResponse response = AuthResponse.builder().auth(accountResponse).build();

        long maxAgeRefreshToken = REFRESH_TOKEN_PERIOD / 1000;
        long maxAgeAccessToken = TOKEN_PERIOD / 1000;

        ResponseCookie refreshCookie = ResponseCookieGenerator.responseCookie(
                "r_auth_token", refreshToken.getToken(), true, true, "/", maxAgeRefreshToken);

        ResponseCookie accessCookie = ResponseCookieGenerator.responseCookie(
                "a_auth_token", accessToken, true, true, "/", maxAgeAccessToken);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshCookie.toString(), accessCookie.toString())
                .body(CommonApiResponse.success(response));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(name = "r_auth_token") String cookieValue) {
        refreshTokenService.deleteByToken(cookieValue);
        SecurityContextHolder.clearContext();

        ResponseCookie refreshCookie = ResponseCookieGenerator.responseCookie("r_auth_token", null, true, true, "/", 0);
        ResponseCookie accessCookie = ResponseCookieGenerator.responseCookie("a_auth_token", null, true, true, "/", 0);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshCookie.toString(), accessCookie.toString())
                .body(CommonApiResponse.success(null));
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
