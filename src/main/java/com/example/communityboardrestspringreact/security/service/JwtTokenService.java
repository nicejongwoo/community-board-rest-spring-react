package com.example.communityboardrestspringreact.security.service;

import com.example.communityboardrestspringreact.security.web.dto.response.TokenResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Service
public class JwtTokenService {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    private Long TOKEN_PERIOD = 1000L * 60L * 10L; //10분
    private Long REFRESH_TOKEN_PERIOD = 1000L * 60L * 60L * 24L * 30L * 3L; //90일

    private Key key;

    @PostConstruct
    void init() {
        byte[] sha = Sha512DigestUtils.sha(jwtSecret.getBytes());
        jwtSecret = Base64.getEncoder().encodeToString(sha);
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public TokenResponse generateToken(Authentication authentication) {

//        AccountSecurityAdapter accountSecurityAdapter = (AccountSecurityAdapter) authentication.getPrincipal();

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        Claims claims = Jwts.claims().setSubject(userDetails.getAccount().getEmail());

        claims.put("roles", Arrays.asList(roles));

        Date now = new Date();

        return TokenResponse.builder()
                .accessToken(
                        Jwts.builder()
                                .setClaims(claims)
                                .setIssuedAt(now)
                                .setExpiration(new Date(now.getTime() + TOKEN_PERIOD))
                                .signWith(key, SignatureAlgorithm.HS512)
                                .compact()
                )
                .refreshToken(
                        Jwts.builder()
                                .setClaims(claims)
                                .setIssuedAt(now)
                                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_PERIOD))
                                .signWith(key, SignatureAlgorithm.HS512)
                                .compact()
                )
                .build();

    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (SignatureException ex) {
            log.error("Invalid JWT signature - {}", ex.getMessage());
        }catch (MalformedJwtException ex) {
            log.error("Invalid JWT token - {}", ex.getMessage());
        }catch (ExpiredJwtException ex) {
            log.error("Expired JWT token - {}", ex.getMessage());
        }catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token - {}", ex.getMessage());
        }catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty - {}", ex.getMessage());
        }

        return false;
    }

    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }
}
